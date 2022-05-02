package org.boothverse.foodpants.business.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.serialization.QuantityMixin;
import org.boothverse.foodpants.business.dao.util.QuantityParser;
import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.*;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeDAO extends JDBCListDAO<Recipe> {

    /**
     * Creates a new RecipeDAO
     */
    public RecipeDAO() {
        super("recipes", new String[]{"id", "name", "foodGroup", "nutrition", "instructions", "ingredients", "servings"});
    }

    /**
     * created to convert a list to a string to store in database
     * @param
     * @return String result
     */
    private String ingredientsToString(List<FoodInstance> ingredients) {
        String result = "";
        for (FoodInstance food : ingredients) {
            result = result + food.getId() + ":" + food.getQuantity().toString() + ",";
        }
        result = result.substring(0, result.length() - 1);
        return result;

    }

    /**
     * created to convert string from database back into list
     * @param
     * @return List<FoodInstance> list
     */
    private List<FoodInstance> stringToIngredients(String ingredients) throws PantsNotParsedException {
        List<FoodInstance> list = new ArrayList<>();
        String[] items = ingredients.split(",");
        for (String s : items) {
            String[] temp = s.split(":");
            list.add(new FoodInstance(temp[0], QuantityParser.parse(temp[1])));
        }
        return list;
    }

    /**
     * Converts object data to SQL
     *
     * @param data
     * @return
     */
    @Override
    protected String[] objToSQL(Recipe data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Quantity.class, QuantityMixin.class);
        try {
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
            return null;
        }
    }

    /**
     * Converts SQL data to object
     *
     * @param rs
     * @return
     */
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
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            String instructions = rs.getString(5);
            List<FoodInstance> ingredients = stringToIngredients(rs.getString(6));
            Double servings = rs.getDouble(7);

            map.put(id, new Recipe(id, name, group, descriptor, instructions, ingredients, servings));
        }

        return map;
    }
}
