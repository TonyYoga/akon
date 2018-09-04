package ru.job4j.parsersqlru;

import org.quartz.*;

import java.util.Date;

public class ParseJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String properties = jobDataMap.getString("properties");
        System.out.println(properties + " " + new Date());
        ParserSqlRu parserSqlRu = new ParserSqlRu(PropertiesReader.getProperties(properties));
        parserSqlRu.parseUrl();
    }
}
