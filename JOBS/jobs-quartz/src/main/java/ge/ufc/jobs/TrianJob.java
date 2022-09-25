package ge.ufc.jobs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ge.ufc.figures.Triangle;
import ge.ufc.figures.Triangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.*;
import java.util.Random;


public class TrianJob implements Job {
    Logger logger = LogManager.getLogger();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        File file = new File("triangles.json");
        if (!file.exists()) {
            try {
                if (!file.createNewFile())
                    throw new IOException();
            } catch (IOException e) {
                logger.error("IO Exception");
                throw new RuntimeException(e);
            }
        }
        Triangle triangles;
        Random random = new Random();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Triangle triangle = new Triangle(random.nextDouble() * 10, random.nextDouble() * 10, random.nextDouble() * 10);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String content = reader.lines().reduce("", (a, b) -> a + b);
            if (!content.isEmpty()) {
                triangles = gson.fromJson(content, Triangle.class);
            } else {
                triangles = new Triangle();
            }
        } catch (IOException e) {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
        triangles.getTriangleList().add(triangle);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(gson.toJson(triangles));
            logger.debug("Json Triangle added: " + triangle);
        } catch (IOException e) {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
    }
}
