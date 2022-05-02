package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.util.QuantityParser;
import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionInstance;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FoodInstanceDAO extends JDBCListDAO<FoodInstance> {

    /**
     * Constructor for FoodInstanceDAO
     *
     * @param name
     */
    public FoodInstanceDAO(String name) {
        super(name, new String[]{"id", "quantity"});
    }

    /**
     * Converts object data to SQL
     *
     * @param data
     * @return
     */
    @Override
    protected String[] objToSQL(FoodInstance data) {
        return new String[]{
            SQLUtils.inQuote(data.getId()),
            SQLUtils.inQuote(data.getQuantity().toString())
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
    protected Map<String, FoodInstance> SQLToObj(ResultSet rs) throws SQLException, PantsNotParsedException {
        Map<String, FoodInstance> data = new HashMap<>();

        while (rs.next()) {
            String id = rs.getString(1);
            Quantity<?> quantity;
            quantity = QuantityParser.parse(rs.getString(2));

            data.put(id, new FoodInstance(id, quantity));
        }
        return data;
    }
}
