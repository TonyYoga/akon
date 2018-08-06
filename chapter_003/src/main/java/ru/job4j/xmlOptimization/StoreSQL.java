package ru.job4j.xmlOptimization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Store and get data from DB
 *  @author  Konkin Anton
 *  @since   1.0
 */

public class StoreSQL implements AutoCloseable{
    private String config;
    private Connection connection;
    private Properties properties;

    public StoreSQL(String config) {
        setConfig(config);
    }

    public void setConfig(String config) {
        this.config = config;
        this.properties = getProperties();
        this.connection = createConnection();
    }

    public Connection getConnection() {
//        String url = "jdbc:sqlite:/home/user/projects/akon/chapter_003/test.db";
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return connection;
    }

        //return this.connection;


    public boolean generate(int i) {
        //do generation og i-fields in DB

        if (i < 1) {
            System.out.println("wrong data");
            return false;
        }
        String sql = "INSERT INTO itab(i) VALUES (?);";
        String sqlDel = "DELETE FROM itab;";


        try (Connection conn = getConnection()) {
            System.out.println("conn for insert is " + getConnection().isClosed());
             //PreparedStatement preparedStatementDel = conn.prepareStatement(sqlDel);
             PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //preparedStatement.executeUpdate();
            //preparedStatementDel.executeUpdate();
            for (int j = 0; j < i; j++) {
                preparedStatement.setInt(1, i);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

    public Properties getProperties() {
        //создать файл проперти со всем инастройками
        Properties prop = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(config);
            prop.load(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public Connection createConnection() {
        String url = "jdbc:sqlite:/home/user/projects/akon/chapter_003/test.db";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement()) {
            //DatabaseMetaData data = conn.getMetaData();
            String sql = "CREATE TABLE IF NOT EXISTS itab (i integer PRIMARY KEY);";
            statement.executeUpdate(sql);
            System.out.println("from create conn is " + conn.isClosed());

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
