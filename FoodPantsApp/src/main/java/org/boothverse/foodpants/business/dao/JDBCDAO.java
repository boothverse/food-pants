package org.boothverse.foodpants.business.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.util.JDBCUtils;

import java.io.*;
import java.sql.*;

public abstract class JDBCDAO {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:db;create=true";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    protected final String table;
    protected final String[] cols;
    protected final String path;

    private static Logger logger = LogManager.getLogger(JDBCDAO.class);

    /**
     * Creates a new JDBCDAO
     *
     * @param table
     * @param cols
     */
    JDBCDAO(String table, String[] cols) {
        this.table = table;
        this.cols = cols;
        this.path = "target/classes/sql/create_" + table + ".sql";
        if (!tableExists()) {
            createTable();
            logger.info(table + " table created");
        }
    }

    public JDBCDAO() {
        this.table = null;
        this.cols = null;
        this.path = null;
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
            logger.info("database driver successfully loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.info("database driver failed to load");
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            logger.info("successful connection to database");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("connection to database failed");
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
        statement.execute("INSERT INTO " + table + " VALUES (" + String.join(", ", data) + ")");
        logger.info("data inserted into " + table);
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
        logger.info("data updated in " + table);
    }

    /**
     * Returns all the data in the table
     *
     * @param statement
     * @return
     * @throws SQLException
     */
    protected ResultSet executeGetAll(Statement statement) throws SQLException {
        logger.info("all data retrieved from " + table);
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
        logger.info(condition + " execution exists in " + table);
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
        statement.execute("DELETE FROM " + table + " WHERE ID='" + id + "'");
        logger.info(id + " removed from " + table);
    }

    /**
     * Checks whether table exists in DB
     *
     * @throws SQLException
     */
    protected boolean tableExists() {
        try (Connection connection = getDBConnection()) {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, table.toUpperCase(), null);
            if (tables.next()) {
                logger.info(table + " table exists");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info(table + " table does not exist");
        }
        return false;
    }

    /**
     * Runs SQL file to create table in DB if it doesn't exist
     *
     * @throws SQLException
     * @throws FileNotFoundException
     */
    protected void createTable() {
        new JDBCUtils().executeScript(this.path);
        logger.info("table created at path " + path);
    }

    public void removeAll() {
        try (Connection conn = getDBConnection(); Statement statement = conn.createStatement()) {
            statement.execute("DELETE FROM " + table);
            logger.info("all data removed from " + table);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("failed to remove all data from " + table);
        }
    }
}
