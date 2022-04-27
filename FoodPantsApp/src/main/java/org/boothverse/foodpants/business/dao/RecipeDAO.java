package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RecipeDAO extends JDBCListDAO<Recipe> {

    RecipeDAO() {
        super("recipes", new String[]{"id", "name", "foodGroup", "nutrition", "instructions", "ingredients", "servings"});
    }

    @Override
    protected String[] objToSQL(Recipe data) {
        // TODO
        return new String[0];
    }

    @Override
    protected Map<String, Recipe> SQLToObj(ResultSet rs) throws SQLException {
        // TODO
        return null;
    }
}
