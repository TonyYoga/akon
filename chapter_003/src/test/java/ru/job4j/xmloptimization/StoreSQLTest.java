package ru.job4j.xmloptimization;


import java.io.File;

public class StoreSQLTest {

    public static void main(String[] args) {

        try (StoreSQL storeSQL = new StoreSQL("/home/user/projects/akon/chapter_003/src/main/resources/storesql.properties")) {
            long start = System.nanoTime();
            int i = 1000;
            storeSQL.generate(i);

            File target = new File("/home/user/projects/akon/chapter_003/base.xml");
            StoreXML storeXML = new StoreXML(target);
            storeXML.save(storeSQL.getData());
            File dest = new File("/home/user/projects/akon/chapter_003/newbase.xml");
            File scheme = new File("/home/user/projects/akon/chapter_003/src/main/resources/scheme.xsl");
            ConvertXSQT convertXSQT = new ConvertXSQT(target, dest, scheme);
            convertXSQT.convert(target, dest, scheme);

            System.out.println(convertXSQT.parseNcalc(dest));

            long end = System.nanoTime();
            long worktime = end - start;
            System.out.printf("For i = %d work time is %d", i, worktime);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}