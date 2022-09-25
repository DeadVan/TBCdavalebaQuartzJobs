package ge.ufc.jobs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ge.ufc.figures.Rectangle;
import ge.ufc.figures.Rectangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.*;
import java.util.Random;


public class RecJob implements Job {
    Logger logger = LogManager.getLogger();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        File file = new File("rectangles.json");
        if(!file.exists()) {
            try {
                if(!file.createNewFile())
                    throw new IOException();
            } catch (IOException e) {
                logger.error("IO Exception");
                throw new RuntimeException(e);
            }
        }
        Rectangle rectangles;
        Random random = new Random();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Rectangle r = new Rectangle(random.nextDouble() * 10,random.nextDouble() * 10);


        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String content = reader.lines().reduce("",(a,b) -> a+b);
            if(!content.isEmpty())
            {
                rectangles = gson.fromJson(content,Rectangle.class);

            }
            else
            {
                rectangles = new Rectangle();

            }
        } catch (IOException e)
        {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
        rectangles.getRectangleList().add(r);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            writer.write(gson.toJson(rectangles));
            logger.debug("Json Rectangle added: " + r);
        } catch (IOException e) {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
    }
}
