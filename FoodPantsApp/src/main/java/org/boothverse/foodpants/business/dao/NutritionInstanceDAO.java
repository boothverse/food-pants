package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.ReportPeriod;
import tec.units.ri.quantity.Quantities;

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
    NutritionInstanceDAO() {
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
    protected Map<String, NutritionInstance> SQLToObj(ResultSet rs) throws SQLException {
        Map<String, NutritionInstance> data = new HashMap<>();

        while (rs.next()) {
            String id = rs.getString(1);
            String foodId = rs.getString(2);
            Quantity<?> quantity = Quantities.getQuantity(rs.getString(3));
            Long consumedAt = rs.getLong(4);

            data.put(id, new NutritionInstance(id, foodId, quantity, new Date(consumedAt)));
        }
        return data;
    }
}
