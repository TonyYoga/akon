package ru.job4j.tracker;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;




public class Tracker implements AutoCloseable {

    private String config;
    private Properties prop;
    private Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);

    public Tracker(String config) {
        //connect to DB

        this.config = config;
        this.prop = getProp(config);
        checkConnection(prop);
    }

    //Check connection and if it need - creating DB and working tables
    private void checkConnection(Properties prop) {
        String url = prop.getProperty("db.host");
        String login = prop.getProperty("db.login");
        String pass = prop.getProperty("db.password");
        String dbName = prop.getProperty("db.name");
        String exists = prop.getProperty("db.exists");
        try (Connection conn = DriverManager.getConnection(url, login, pass);
             PreparedStatement statement = conn.prepareStatement(exists)) {

            Class.forName("org.postgresql.Driver");
            statement.setString(1, dbName);
            ResultSet rs = statement.executeQuery();
            rs.next();

            if (!rs.getBoolean("exists")) {
                Statement st = conn.createStatement();
                st.executeUpdate(prop.getProperty("db.create"));
                connection = DriverManager.getConnection(url + dbName, login, pass);
                st = connection.createStatement();
                st.executeUpdate(prop.getProperty("tb.create"));

            }
            connection = DriverManager.getConnection(url + dbName, login, pass);

        } catch (ClassNotFoundException| SQLException ex) {

            LOG.error(ex.getMessage(), ex);
        }


    }

    public boolean add(Item item) {


        if (findById(item.getId()) != null) {
            System.out.println("В базе уже существует заявка с таким ИД");
            return false;
        }


        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO items (iid, iname, idesk, icreate, icomm) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, item.getId());
            statement.setString(2, item.getName());
            statement.setString(3, item.getDesk());
            statement.setTimestamp(4, new Timestamp(item.getCreate()));
            statement.setArray(5, connection.createArrayOf("VARCHAR", item.getComments()));
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }



        return true;
    }

    public boolean update(Item item) {

        if (findById(item.getId()) == null) {
            System.out.println("В базе нет заявки с таким ИД");
            //there will be code with option to add item
            return false;
        }


        try (PreparedStatement statement = connection.prepareStatement(prop.getProperty("sql.upd"), Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, item.getName());
            statement.setString(2, item.getDesk());
            statement.setTimestamp(3, new Timestamp(item.getCreate()));
            statement.setArray(4, connection.createArrayOf("VARCHAR", item.getComments()));
            statement.setString(5, item.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return true;
    }

    public Item findById(String id) {

        try (PreparedStatement statement = connection.prepareStatement(prop.getProperty("sql.fbId"));) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getString("iid").equals(id)) {
                    return new Item(
                            rs.getString("iid"),
                            rs.getString("iname"),
                            rs.getString("idesk"),
                            rs.getTimestamp("icreate").getTime(),
                            (String[]) rs.getArray("icomm").getArray());
                }
            }
            rs.close();



        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }

    public boolean delete(String id) {
        if (findById(id) == null) {
            System.out.println("В базе нет заявки с таким ИД");
            //there will be code with option to add item
            return false;
        }


        try (PreparedStatement statement = connection.prepareStatement(prop.getProperty("sql.del"), Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return true;
    }

    public ArrayList<Item> getAll() {
        try (PreparedStatement statement = connection.prepareStatement(prop.getProperty("sql.getall"));) {
            ArrayList<Item> items = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                //  public Item(String id, String name, String desk, long create, String[] comments)
                items.add(new Item(
                        rs.getString("iid"),
                        rs.getString("iname"),
                        rs.getString("idesk"),
                        rs.getTimestamp("icreate").getTime(),
                        (String[]) rs.getArray("icomm").getArray()));
            }
            rs.close();
            return items;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }


        return null;
    }

    //Method for get configs from resourse file
    private Properties getProp(String config) {

        Properties prop = new Properties();
        try (InputStream inputStream =  new FileInputStream(config)) {

            prop.load(inputStream);

        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return prop;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }

    }
}
