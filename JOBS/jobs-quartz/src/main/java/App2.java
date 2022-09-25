import ge.ufc.jobs.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class App2 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail circJob = JobBuilder.newJob(CircJob.class).withIdentity("CircJob","groupCirc").build();
            Trigger TriggerCirc = TriggerBuilder.newTrigger().withIdentity("TriggerCirc","groupCirc").startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(4)).build();

            JobDetail tirnJob = JobBuilder.newJob(TrianJob.class).withIdentity("TrianJob","groupTrian").build();
            Trigger TriggerTrian = TriggerBuilder.newTrigger().withIdentity("TriggerTrian","groupTrian").startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(7).withRepeatCount(5)).build();

            JobDetail rectJob = JobBuilder.newJob(RecJob.class).withIdentity("RecJob","groupRec").build();
            Trigger TriggerRect = TriggerBuilder.newTrigger().withIdentity("TriggerRect","groupRec").startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(6).withRepeatCount(3)).build();
            scheduler.scheduleJob(circJob,TriggerCirc);
            scheduler.scheduleJob(tirnJob,TriggerTrian);
            scheduler.scheduleJob(rectJob,TriggerRect);
            scheduler.start();
            try{
                Thread.sleep(50_000);
                scheduler.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }


    }
}
