package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * An abstract class which holds methods and attributes necessary for derby database connection
 *
 * @param <T> the type of object the dao is operating on
 */
abstract class JDBCSingleDAO<T> extends JDBCDAO implements SingleDAO<T> {

    protected static final Long ID = 1L;

    /**
     * Creates a new JDBCSingleDAO
     *
     * @param table a string representing a table
     * @param cols the column names of the table
     */
    JDBCSingleDAO(String table, String[] cols) {
        super(table, cols);
    }

    protected abstract String[] objToSQL(T data);
    protected abstract T SQLToObj(ResultSet rs) throws SQLException, PantsNotParsedException;

    @Override
    public void save(T data) {
        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            String condition = "id = " + ID;
            Boolean exists = executeExists(statement, condition);
            if (exists) {
                executeUpdate(statement, objToSQL(data), condition);
            } else {
                executeInsert(statement, objToSQL(data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T load() {
        T data = null;

        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            ResultSet rs = executeGetAll(statement);
            data = SQLToObj(rs);
        } catch (SQLException | PantsNotParsedException e) {
            e.printStackTrace();
        }

        return data;
    }
}
