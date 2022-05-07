package org.boothverse.foodpants.business.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.persistence.IdObject;
import org.boothverse.foodpants.business.dao.util.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

abstract class JDBCListDAO<T extends IdObject> extends JDBCDAO implements ListDAO<T> {

    Logger logger = LogManager.getLogger(JDBCListDAO.class);

    /**
     * Creates a new JDBCListDAO
     *
     * @param table
     * @param cols
     */
    JDBCListDAO(String table, String[] cols) {
        super(table, cols);
    }

    protected abstract String[] objToSQL(T data);
    protected abstract Map<String, T> SQLToObj(ResultSet rs) throws SQLException, PantsNotParsedException;

    /**
     * Saves the specified data in the DB
     *
     * @param data
     */
    @Override
    public void save(T data) {
        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            String condition = "id = " + SQLUtils.inQuote(data.getId());
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
     * Loads the data in the DB
     *
     * @return
     */
    @Override
    public Map<String, T> load() {
        Map<String, T> data = new HashMap<>();

        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            ResultSet rs = executeGetAll(statement);
            data = SQLToObj(rs);
            logger.info("data loaded into " + table);
        } catch (SQLException | PantsNotParsedException e) {
            e.printStackTrace();
            logger.info("data failed to load into " + table);
        }

        return data;
    }

    /**
     * Removes the specified data from the DB
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            executeRemove(statement, id);
            logger.info(id + " removed from table" + table);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info(id + " failed to remove from table " + table);
        }
    }
}
