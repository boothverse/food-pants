package org.boothverse.foodpants.business.dao;

import java.sql.*;

abstract class JDBCDAO {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:db;";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    protected final String table;
    protected final String[] cols;

    /**
     * Creates a new JDBCDAO
     *
     * @param table
     * @param cols
     */
    JDBCDAO(String table, String[] cols) {
        this.table = table;
        this.cols = cols;
    }

    /**
     * Establishes a connection to the DB
     *
     * @return
     */
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

    /**
     * Inserts the given data into the DB
     *
     * @param statement
     * @param data
     * @throws SQLException
     */
    protected void executeInsert(Statement statement, String[] data) throws SQLException {
        System.out.println(data);
        statement.execute("INSERT INTO " + table + " VALUES (" + String.join(", ", data) + ")");
    }

    /**
     * Updates the given data in the DB
     *
     * @param statement
     * @param data
     * @param condition
     * @throws SQLException
     */
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

    /**
     * Returns all the data in the table
     *
     * @param statement
     * @return
     * @throws SQLException
     */
    protected ResultSet executeGetAll(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM " + table);
    }

    /**
     * Determine whether the specified data is in the DB
     *
     * @param statement
     * @param condition
     * @return
     * @throws SQLException
     */
    protected Boolean executeExists(Statement statement, String condition) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM " + table + " WHERE " + condition);
        return rs.next();
    }

    /**
     * Removes the specified piece of data from the DB
     *
     * @param statement
     * @param id
     * @throws SQLException
     */
    protected void executeRemove(Statement statement, String id) throws SQLException {
        statement.execute("DELETE FROM " + table + " WHERE ID=" + id);
    }
}
