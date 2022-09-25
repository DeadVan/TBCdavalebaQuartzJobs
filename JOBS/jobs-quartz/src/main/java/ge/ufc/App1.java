package ge.ufc;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;


public class App1 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();

            try {
                Thread.sleep(40_000);
            } catch (Exception e) {
                System.out.println(e);
            }

            scheduler.shutdown(true);
            SchedulerMetaData metaData = scheduler.getMetaData();
            logger.info("ecxecuted" + metaData.getNumberOfJobsExecuted() + "Jobs.");
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
