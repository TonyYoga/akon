package ru.job4j;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YandexTaskLogger {

    static final Logger LOG = Logger.getLogger(YandexTaskLogger.class);


    public void logmaker(String processname) {

        LOG.info(processname + " - do something");


    }

    public int printLogCounter(long period) {
        File file = new File("myproject.log");
        String str = null;
        int counter = 0;
        //1000*60 = 60000 милисек
        String methodname = "test";
        String patternString = "([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2},[0-9]{3})[\\W\\w]*(" + methodname + ")[\\W\\w]*";
        Pattern pattern = Pattern.compile(patternString);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date date = new Date();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            long curTime = date.getTime();
            while (br.ready()) {
                str = br.readLine();
                Matcher matcher = pattern.matcher(str);
                matcher.matches();
//                System.out.println(str);
//                System.out.println(matcher.group(1));
                Date t = dateFormat.parse(matcher.group(1));
                if (t.getTime() > (curTime - period)) {
                    counter++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    return counter;
    }

}
