package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.util.QuantityUtils;
import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages connecting and interacting with the food instance database
 */
public class FoodInstanceDAO extends JDBCListDAO<FoodInstance> {

    /**
     * Constructor for FoodInstanceDAO
     *
     * @param name the name of the new food instance
     */
    public FoodInstanceDAO(String name) {
        super(name, new String[]{"id", "quantity"});
    }

    @Override
    protected String[] objToSQL(FoodInstance data) {
        return new String[]{
            SQLUtils.inQuote(data.getId()),
            SQLUtils.inQuote(QuantityUtils.toString(data.getQuantity()))
        };
    }

    @Override
    protected Map<String, FoodInstance> SQLToObj(ResultSet rs) throws SQLException, PantsNotParsedException {
        Map<String, FoodInstance> data = new HashMap<>();

        while (rs.next()) {
            String id = rs.getString(1);
            Quantity<?> quantity;
            quantity = QuantityUtils.parse(rs.getString(2));

            data.put(id, new FoodInstance(id, quantity));
        }
        return data;
    }
}
