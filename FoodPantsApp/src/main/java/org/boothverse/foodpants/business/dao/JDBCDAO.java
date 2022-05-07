package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.util.JDBCUtils;

import java.io.*;
import java.sql.*;

/**
 * An abstract class which holds methods and attributes necessary for derby database connection
 */
public abstract class JDBCDAO {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:db;create=true";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    protected final String table;
    protected final String[] cols;
    protected final String path;

    /**
     * Creates a new JDBCDAO
     *
     * @param table a string representing a table
     * @param cols the column names of the table
     */
    JDBCDAO(String table, String[] cols) {
        this.table = table;
        this.cols = cols;
        this.path = "target/classes/sql/create_" + table + ".sql";
        if (!tableExists()) {
            createTable();
        }
    }

    /**
     * Creates a new empty JDBCDAO
     */
    public JDBCDAO() {
        this.table = null;
        this.cols = null;
        this.path = null;
    }

    protected static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    protected void executeInsert(Statement statement, String[] data) throws SQLException {
        statement.execute("INSERT INTO " + table + " VALUES (" + String.join(", ", data) + ")");
    }

    protected void executeUpdate(Statement statement, String[] data, String condition) throws SQLException {
        StringBuilder updateBuilder = new StringBuilder();
        int i = 0;
        while (i < cols.length - 1) {
            updateBuilder.append(cols[i]);
            updateBuilder.append(" = ");
            updateBuilder.append(data[i]);
            updateBuilder.append(", ");
            i++;
        }
        updateBuilder.append(cols[i]);
        updateBuilder.append(" = ");
        updateBuilder.append(data[i]);

        statement.execute("UPDATE " + table + " SET " + updateBuilder + " WHERE " + condition);
    }

    protected ResultSet executeGetAll(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM " + table);
    }

    protected Boolean executeExists(Statement statement, String condition) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM " + table + " WHERE " + condition);
        return rs.next();
    }

    protected void executeRemove(Statement statement, String id) throws SQLException {
        statement.execute("DELETE FROM " + table + " WHERE ID='" + id + "'");
    }

    protected boolean tableExists() {
        try (Connection connection = getDBConnection()) {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, table.toUpperCase(), null);
            if (tables.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void createTable() {
        new JDBCUtils().executeScript(this.path);
    }

    public void removeAll() {
        try (Connection conn = getDBConnection(); Statement statement = conn.createStatement()) {
            statement.execute("DELETE FROM " + table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
