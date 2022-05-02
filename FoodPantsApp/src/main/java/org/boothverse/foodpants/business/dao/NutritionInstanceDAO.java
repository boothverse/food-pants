package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.util.QuantityParser;
import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.NutritionInstance;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NutritionInstanceDAO extends JDBCListDAO<NutritionInstance> {

    /**
     * Constructor for NutritionInstanceDAO
     */
    public NutritionInstanceDAO() {
        super("nutritionInstances", new String[]{"id", "foodId", "quantity", "consumedAt"});
    }

    /**
     * Converts object data to SQL
     *
     * @param data
     * @return
     */
    @Override
    protected String[] objToSQL(NutritionInstance data) {
        return new String[]{
            SQLUtils.inQuote(data.getId()),
            SQLUtils.inQuote(data.getFoodId()),
            SQLUtils.inQuote(data.getQuantity().toString()),
            data.getConsumedAt().getTime() + ""
        };
    }

    /**
     * Converts SQL to object data
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    @Override
    protected Map<String, NutritionInstance> SQLToObj(ResultSet rs) throws SQLException, PantsNotParsedException {
        Map<String, NutritionInstance> data = new HashMap<>();

        while (rs.next()) {
            String id = rs.getString(1);
            String foodId = rs.getString(2);
            Quantity<?> quantity = QuantityParser.parse(rs.getString(3));
            Long consumedAt = rs.getLong(4);

            data.put(id, new NutritionInstance(id, foodId, quantity, new Date(consumedAt)));
        }
        return data;
    }
}
