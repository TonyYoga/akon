package ru.job4j.xmlOptimization;

import static org.junit.Assert.*;

public class StoreSQLTest {

    public static void main(String args[]) {
        StoreSQL storeSQL = new StoreSQL("chapter_003/src/main/resources/storesql.properties");
        storeSQL.generate(10);
    }

}