package ru.job4j.array;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {

    private Connection connection;

    public StoreSQL(Config config) throws SQLException {
        this.connection = DriverManager.getConnection(config.get("url"));
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void generate(int size) {
        if (!isTableEntryExist()) {
            createTableEntry();
        }
        clearTableEntry();
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO entry VALUES (?);")) {
            connection.setAutoCommit(false);
            for (int i = 1; i <= size; i++) {
                st.setInt(1, i);
                st.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM entry");
            while (rs.next()) {
                result.add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean isTableEntryExist() {
        boolean tableExists = false;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tableRS = metaData.getTables(
                    null,
                    null,
                    "entry",
                    new String[] {"TABLE"}
                    );
            if (tableRS.next()) {
                tableExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableExists;
    }

    private void createTableEntry() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(
                    "CREATE TABLE entry ("
                            + "field integer"
                            + ");"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearTableEntry() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM entry;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
