package ru.job4j.parsersqlru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

/**
 * Store and get data
 *  @author  Konkin Anton
 *  @since   1.0
 */

public class StoreData implements AutoCloseable {

    private Connection connection;
    private Properties properties;
    private static final Logger LOG = LoggerFactory.getLogger(StoreData.class);

    public class CheckDate {
        private boolean isFirstPass;
        private long lastDate;

        public CheckDate(boolean isFirstJob, long lastDate) {
            this.isFirstPass = isFirstJob;
            this.lastDate = lastDate;
        }

        public boolean isFirstPass() {
            return isFirstPass;
        }

        public long getLastDate() {
            return lastDate;
        }
    }

    public StoreData(Properties properties) {
        this.properties = properties;
        try {
            getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getConnection() throws SQLException{
        //establishing connection to DB
        String url = properties.getProperty("db.host");
        connection = DriverManager.getConnection(url);
        try (Statement statement = connection.createStatement()) {
            Class.forName("org.sqlite.JDBC");
            String sql = "CREATE TABLE IF NOT EXISTS vacancy (vText VARCHAR(300), vURL VARCHAR(300), vDate TIMESTAMP);";
            statement.executeUpdate(sql);
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            connection.commit();
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
            connection.rollback();
        }
    }

    public CheckDate checkLastDate() throws SQLException {
        String sql = "SELECT MAX(vDate) FROM vacancy;";
        Calendar cal = Calendar.getInstance();
        if (connection.isClosed()) {
            getConnection();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            //ResultSet rs = statement.executeQuery();
            rs.next();
            rs.getLong(1);
            if (!rs.wasNull()) {
                return new CheckDate(false, rs.getLong(1)); //have already rows in DB
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        cal.set(Year.now().getValue(), Calendar.JANUARY, 1, 0, 0);
        return new CheckDate(true, cal.getTimeInMillis()); //its First Pass
    }

    public void storeData(ArrayList<Vacancy> array) throws SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO vacancy (vText, vURL, vDate) VALUES (?, ?, ?)");
        for (int i = 0; i < array.size() - 1; i++) {
            sql.append(", (?, ?, ?)");
        }
        sql.append(";");
        if (connection.isClosed()) {
            getConnection();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < array.size(); i++) {
                statement.setString((3 * i) + 1, array.get(i).getText());
                statement.setString((3 * i) + 2, array.get(i).getUrl());
                statement.setLong((3 * i) + 3, array.get(i).getDate());
            }
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            LOG.error(e.getMessage(), e);
        }
    }
    public boolean isAdded(Vacancy vacancy) throws SQLException {
        String sql = "SELECT * FROM vacancy WHERE vURL = ?;";
        if (connection.isClosed()) {
            getConnection();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vacancy.getUrl());
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
