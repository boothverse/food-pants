package org.boothverse.foodpants.business.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.serialization.QuantityMixin;
import org.boothverse.foodpants.business.dao.util.QuantityUtils;
import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.*;

import javax.measure.Quantity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages connecting and interacting with the recipe database
 */
public class RecipeDAO extends JDBCListDAO<Recipe> {

    private static Logger logger = LogManager.getLogger(RecipeDAO.class);

    /**
     * Creates a new RecipeDAO
     */
    public RecipeDAO() {
        super("recipes", new String[]{"id", "name", "foodGroup", "nutrition", "instructions", "ingredients", "servings"});
    }

    private String ingredientsToString(List<FoodInstance> ingredients) {
        String result = "";
        for (FoodInstance food : ingredients) {
            result = result + food.getId() + ":" + food.getQuantity().toString() + ",";
        }
        if(!ingredients.isEmpty()) {
            result = result.substring(0, result.length() - 1);
        }
        logger.info("ingredients list converted to string");
        return result;

    }

    private List<FoodInstance> stringToIngredients(String ingredients) throws PantsNotParsedException {
        List<FoodInstance> list = new ArrayList<>();
        String[] items = ingredients.split(",");
        for (String s : items) {
            String[] temp = s.split(":");
            list.add(new FoodInstance(temp[0], QuantityUtils.parse(temp[1])));
        }
        logger.info("string converted to list of ingredients");
        return list;
    }

    @Override
    protected String[] objToSQL(Recipe data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Quantity.class, QuantityMixin.class);
        try {
            logger.info(data.getId() + " recipe converted to SQL format");
            return new String[]{
                SQLUtils.inQuote(data.getId()),
                SQLUtils.inQuote(data.getName()),
                SQLUtils.inQuote(data.getFoodGroup().toString()),
                SQLUtils.inQuote(mapper.writeValueAsString(data.getNutrition())),
                SQLUtils.inQuote(data.getInstructions()),
                SQLUtils.inQuote(ingredientsToString(data.getIngredients())),
                SQLUtils.inQuote(data.getServings().toString())};
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(data.getId() + " failed to convert to SQL format");
            return null;
        }
    }

    @Override
    protected Map<String, Recipe> SQLToObj(ResultSet rs) throws SQLException, PantsNotParsedException {
        Map<String, Recipe> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Quantity.class, QuantityMixin.class);

        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            FoodGroup group = FoodGroup.valueOf(rs.getString(3));
            NutritionDescriptor descriptor = null;
            try {
                descriptor = mapper.readValue(rs.getString(4), NutritionDescriptor.class);
                logger.info("descriptor correctly read from mapper");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error("descriptor incorrectly read from mapper");
            }
            String instructions = rs.getString(5);
            List<FoodInstance> ingredients = stringToIngredients(rs.getString(6));
            Double servings = rs.getDouble(7);

            map.put(id, new Recipe(id, name, group, descriptor, instructions, ingredients, servings));
            logger.info("recipe " + id + " converted from SQL, added to map");
        }

        return map;
    }
}
