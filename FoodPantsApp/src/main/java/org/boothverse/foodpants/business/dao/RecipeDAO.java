package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RecipeDAO extends JDBCListDAO<Recipe> {

    /**
     * Constructs RecipeDAO
     */
    public RecipeDAO() {
        super("recipes", new String[]{"id", "name", "foodGroup", "nutrition", "instructions", "ingredients", "servings"});
    }

    @Override
    protected String[] objToSQL(Recipe data) {
        return new String[]{
            SQLUtils.inQuote(data.getId()),
            SQLUtils.inQuote(data.getName()),
            SQLUtils.inQuote(data.getFoodGroup().toString()),

        };
    }

    @Override
    protected Map<String, Recipe> SQLToObj(ResultSet rs) throws SQLException {
        // TODO
        return null;
    }
}
