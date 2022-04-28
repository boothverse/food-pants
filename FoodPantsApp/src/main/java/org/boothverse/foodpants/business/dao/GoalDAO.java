package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.persistence.NutritionType;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GoalDAO extends JDBCListDAO<Goal> {
    /**
     * Constructor for NutritionInstanceDAO
     */
    public GoalDAO() {
        super("goals", new String[]{"id", "goalType", "dailyQuantity", "nutritionType"});
    }

    /**
     * Converts object data to SQL
     *
     * @param data
     * @return
     */
    @Override
    protected String[] objToSQL(Goal data) {
        return new String[]{
            SQLUtils.inQuote(data.getId()),
            SQLUtils.inQuote(data.getGoalType().toString()),
            SQLUtils.inQuote(data.getDailyQuantity().toString()),
            SQLUtils.inQuote(data.getNutritionType().toString())
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
    protected Map<String, Goal> SQLToObj(ResultSet rs) throws SQLException {
        Map<String, Goal> data = new HashMap<>();

        while (rs.next()) {
            String id = rs.getString(1);
            GoalType goalType = GoalType.valueOf(rs.getString(2));
            Quantity<?> quantity = Quantities.getQuantity(rs.getString(3));
            NutritionType nutritionType = NutritionType.valueOf(rs.getString(4));

            data.put(id, new Goal(id, goalType, quantity, nutritionType));
        }
        return data;
    }
}
