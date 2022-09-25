import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;


public class App {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();

           
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
