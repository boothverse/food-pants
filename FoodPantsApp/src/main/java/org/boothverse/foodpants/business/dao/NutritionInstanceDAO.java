package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.NutritionInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class NutritionInstanceDAO extends JDBCListDAO<NutritionInstance> {

    NutritionInstanceDAO() {
        super("nutritionInstances", new String[]{"id", "foodId", "quantity", "consumedAt"});
    }

    @Override
    protected String[] objToSQL(NutritionInstance data) {
        // TODO
        return new String[0];
    }

    @Override
    protected Map<String, NutritionInstance> SQLToObj(ResultSet rs) throws SQLException {
        // TODO
        return null;
    }
}
