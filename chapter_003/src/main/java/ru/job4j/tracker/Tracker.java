package ru.job4j.tracker;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;



public class Tracker implements AutoCloseable{

    //private Item item;
    private String config;
    private Properties prop;
    private Connection connection;
    private static final Logger log = LoggerFactory.getLogger(Tracker.class);

    public Tracker(String config) {
        //connect to DB
        //prop = getProp("config.properties");
        this.config = config;
        this.prop = getProp(config);
        this.connection = checkConnection(prop);
    }

    // check if data base not created - create it and return true
//    private boolean checkNcreateBase() {
//        return true;
//    }

    private Connection checkConnection(Properties prop) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = prop.getProperty("db.host");
        String login = prop.getProperty("db.login");
        String pass = prop.getProperty("db.password");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, login, pass);
        }
        catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        return conn;
    }

    public boolean add(Item item) {
        //Connection conn;
        //Properties properties = getProp("config.properties");
        //conn = checkConnection(prop);

//        if (findById(item.getId()) != null) {
//            return false;
//        }


        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO items (iid, iname, idesk, icreate, icomm) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getId());
            statement.setString(2, item.getName());
            statement.setString(3, item.getDesk());
            statement.setTimestamp(4, new Timestamp(item.getCreate()));
            statement.setArray(5, connection.createArrayOf("VARCHAR", item.getComments()));

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
            }



        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }



        return true;
    }

    public boolean update(Item item) {
        return true;
    }

    public Item findById(String id) {
        Connection conn;
        Properties properties = getProp(config);
        //connection// = checkConnection(properties);

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE iid = ?");
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Item(
                        rs.getString("iid"),
                        rs.getString("iname"),
                        rs.getString("idesk"),
                        rs.getTimestamp("icreate").getTime(),
                        (String[]) rs.getArray("icomm").getArray());
            }
            //rs.wasNull();


        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

//        if(conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                log.error(e.getMessage(), e);
//            }
//        }
        return null;
    }

    public boolean delete(String id) {
        return true;
    }

    public Item[] getAll() {
        return null;
    }

    private Properties getProp(String config) {
        Properties prop = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(config);
            //inputStream = new FileInputStream("/home/user/projects/akon/chapter_003/src/main/resources/config.properties");
            prop.load(inputStream);

        }
        catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        }
        return prop;
    }

    @Override
    public void close() throws Exception {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }

    }
}
