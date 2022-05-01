package org.boothverse.foodpants.business.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.boothverse.foodpants.business.dao.serialization.QuantityMixin;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.business.dao.util.*;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionDescriptor;

import javax.measure.Quantity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FoodDAO extends JDBCListDAO<Food> {

    /**
     * Creates a new FoodDAO
     */
    public FoodDAO() {
        super("foods", new String[]{"id", "name", "foodGroup", "nutrition"});
    }

    /**
     * Converts object data to SQL
     *
     * @param data
     * @return
     */
    @Override
    protected String[] objToSQL(Food data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Quantity.class, QuantityMixin.class);
        try {
            return new String[]{
                SQLUtils.inQuote(data.getId()),
                SQLUtils.inQuote(data.getName()),
                SQLUtils.inQuote(data.getFoodGroup().toString()),
                SQLUtils.inQuote(mapper.writeValueAsString(data.getNutrition())),
            };
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
     * @throws SQLException
     */
    @Override
    protected Map<String, Food> SQLToObj(ResultSet rs) throws SQLException {
        Map<String, Food> data = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Quantity.class, QuantityMixin.class);
        try {
           while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                FoodGroup group = FoodGroup.valueOf(rs.getString(3));
                NutritionDescriptor nutrition = mapper.readValue(rs.getString(4), NutritionDescriptor.class);

                data.put(id, new Food(id, name, group, nutrition));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }
}