package ru.job4j.xmloptimization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Store and get data from DB
 *  @author  Konkin Anton
 *  @since   1.0
 */

public class StoreSQL implements AutoCloseable {
    private String config;
    private Connection connection;
    private Properties properties;

    public StoreSQL(String config) {
        setConfig(config);
    }

    public void setConfig(String config) {
        this.config = config;
        this.properties = getProperties();

        try {
            getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getConnection() throws SQLException {
        //establishing connection to DB
        String url = properties.getProperty("db.host");
        try  {
            connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            Class.forName("org.sqlite.JDBC");
            String sql = "CREATE TABLE IF NOT EXISTS itab (i integer PRIMARY KEY);";
            statement.executeUpdate(sql);
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            connection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }


    public void generate(int counter) throws SQLException {
        //do generation og i-fields in DB

        if (counter < 1) {
            throw  new IllegalStateException("wrong data...");
        }
        String sql = "INSERT INTO itab(i) VALUES (?);";
        String sqlDel = "DELETE FROM itab;";
        try (Connection conn = connection;
             PreparedStatement preparedStatementDel = conn.prepareStatement(sqlDel);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            //System.out.println("connection " + conn.isClosed());
            preparedStatementDel.executeUpdate();
            for (int j = 0; j < counter; j++) {
                preparedStatement.setInt(1, j);
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    public List<StoreXML.Field> getData() throws SQLException {
        //get data from DB and save it array<Field>
        List<StoreXML.Field> fields = new ArrayList<>();
        String sqlSel = "SELECT * FROM itab;";
        if (connection.isClosed()) {
            getConnection();
        }
        try (PreparedStatement statement = connection.prepareStatement(sqlSel)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                fields.add(new StoreXML.Field(rs.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fields;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

    public Properties getProperties() {
        //get propertyes and sql schemes from resourse file
        Properties prop = new Properties();

        try (InputStream inputStream =  new FileInputStream(config);) {
            prop.load(inputStream);
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
