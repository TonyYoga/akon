package ru.job4j.parsersqlru;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

public class ParserSqlRuApp {
    /**
     *  Entering point of application. SQL.RU Parser
     *  author  Konkin Anton
     *  @since   1.0
     *
     *
     *  example how to start app
     *  java -jar ParserSqlRuApp app.properties
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(ParserSqlRuApp.class);

    public static void main(String[] args) {
        String propFile = args[0];
        Properties prop = PropertiesReader.getProperties(propFile);
        JobDetail job = JobBuilder.newJob(ParseJob.class).usingJobData("properties", propFile).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("SQL.ru parser").withSchedule(CronScheduleBuilder.cronSchedule(prop.getProperty("cron.time"))).build();

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
