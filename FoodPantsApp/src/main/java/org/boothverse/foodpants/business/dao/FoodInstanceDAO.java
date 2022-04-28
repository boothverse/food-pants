package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.FoodInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class FoodInstanceDAO extends JDBCListDAO<FoodInstance> {

    public FoodInstanceDAO(String name) {
        super(name, new String[]{"id", "quantity"});
    }

    @Override
    protected String[] objToSQL(FoodInstance data) {
        // TODO
        return new String[0];
    }

    @Override
    protected Map<String, FoodInstance> SQLToObj(ResultSet rs) throws SQLException {
        // TODO
        return null;
    }
}
