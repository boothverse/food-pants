package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.IdObject;
import org.boothverse.foodpants.business.dao.util.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

abstract class JDBCListDAO<T extends IdObject> extends JDBCDAO implements ListDAO<T> {

    JDBCListDAO(String table, String[] cols) {
        super(table, cols);
    }

    protected abstract String[] objToSQL(T data);
    protected abstract Map<String, T> SQLToObj(ResultSet rs) throws SQLException;

    @Override
    public void save(T data) {
        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            String condition = "id = " + SQLUtils.inQuote(data.getId());
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
    public Map<String, T> load() {
        Map<String, T> data = new HashMap<>();

        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            ResultSet rs = executeGetAll(statement);
            data = SQLToObj(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public void remove(String id) {
        try (Connection con = getDBConnection(); Statement statement = con.createStatement()) {
            executeRemove(statement, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
