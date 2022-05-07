package org.boothverse.foodpants.business.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class JDBCSingleDAO<T> extends JDBCDAO implements SingleDAO<T> {

    private static Logger logger = LogManager.getLogger(JDBCSingleDAO.class);

    protected static final Long ID = 1L;

    /**
     * Creates a new JDBCSingleDAO
     *
     * @param table
     * @param cols
     */
    JDBCSingleDAO(String table, String[] cols) {
        super(table, cols);
    }

    protected abstract String[] objToSQL(T data);
    protected abstract T SQLToObj(ResultSet rs) throws SQLException, PantsNotParsedException;

    /**
     * Saves the specified data in the DB
     *
     * @param data
     */
    @Override
    public void save(T data) {
        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            String condition = "id = " + ID;
            Boolean exists = executeExists(statement, condition);
            if (exists) {
                executeUpdate(statement, objToSQL(data), condition);
                logger.info(condition + " was updated in " + table);
            } else {
                executeInsert(statement, objToSQL(data));
                logger.info(condition + " was added to " + table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("save failed in " + table);
        }
    }

    /**
     * Loads data from the DB
     *
     * @return
     */
    @Override
    public T load() {
        T data = null;

        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            ResultSet rs = executeGetAll(statement);
            data = SQLToObj(rs);
            logger.info("data loaded into " + table);
        } catch (SQLException | PantsNotParsedException e) {
            e.printStackTrace();
            logger.info("data failed to laod into " + table);
        }

        return data;
    }
}
