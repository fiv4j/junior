package ru.job4j.tracker;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("tracker.properties")) {
            Properties config = new Properties();
            if (in == null) {
                System.out.println("Wrong properties.");
                return false;
            }
            config.load(in);
            Class.forName(config.getProperty("driver-class"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );

            if (!isDBStructureExist()) {
                createDBStructure();
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return this.connection != null;
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item add(Item currItem) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO item (id, name) VALUES (?, ?)"
        )) {
            currItem.setId(this.generateId());
            statement.setString(1, currItem.getId());
            statement.setString(2, currItem.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currItem;
    }

    @Override
    public boolean replace(String id, Item currItem) {
        int rowsChanged = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE item SET name = ? WHERE id = ?"
        )) {
            statement.setString(1, currItem.getName());
            statement.setString(2, id);
            rowsChanged = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsChanged > 0;
    }

    @Override
    public boolean delete(String id) {
        int rowsChanged = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM item WHERE id = ?"
        )) {
            statement.setString(1, id);
            rowsChanged = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsChanged > 0;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item");
            while (resultSet.next()) {
                Item currItem = new Item(resultSet.getString("name"));
                currItem.setId(resultSet.getString("id"));
                items.add(currItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM item WHERE name = ?"
        )) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item currItem = new Item(resultSet.getString("name"));
                currItem.setId(resultSet.getString("id"));
                items.add(currItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM item WHERE id = ?"
        )) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = new Item(resultSet.getString("name"));
                result.setId(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean isDBStructureExist() {
        boolean result = false;
        try {
            ResultSet tablesResultSet = connection.getMetaData().getTables(
                    null, null, "item", null);
            if (tablesResultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void createDBStructure() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE item ("
                            + "id VARCHAR(20) PRIMARY KEY, "
                            + "name VARCHAR(100) NOT NULL "
                            + ");"
            );
        }
    }

    private String generateId() {
        Random rnd = new Random();
        return String.valueOf(System.currentTimeMillis() + rnd.nextLong());
    }

    public static void main(String[] args) {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            System.out.println(sql.replace("-421752211779346", new Item("Anna")));
        }
    }
}
