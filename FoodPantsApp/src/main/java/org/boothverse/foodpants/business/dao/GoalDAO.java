package org.boothverse.foodpants.business.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.util.QuantityUtils;
import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.persistence.NutritionType;

import javax.measure.Quantity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages connecting and interacting with the goal database
 */
public class GoalDAO extends JDBCListDAO<Goal> {
    private static Logger logger = LogManager.getLogger(GoalDAO.class);
    /**
     * Constructor for NutritionInstanceDAO
     */
    public GoalDAO() {
        super("goals", new String[]{"id", "goalType", "dailyQuantity", "nutritionType"});
    }

    @Override
    protected String[] objToSQL(Goal data) {
        logger.debug("goal " + data.getId() + " converted to SQL format");
        return new String[]{
            SQLUtils.inQuote(data.getId()),
            SQLUtils.inQuote(data.getGoalType().toString()),
            SQLUtils.inQuote(data.getDailyQuantity().toString()),
            SQLUtils.inQuote(data.getNutritionType().toString())
        };
    }

    @Override
    protected Map<String, Goal> SQLToObj(ResultSet rs) throws SQLException, PantsNotParsedException {
        Map<String, Goal> data = new HashMap<>();

        while (rs.next()) {
            String id = rs.getString(1);
            GoalType goalType = GoalType.valueOf(rs.getString(2));
            Quantity<?> quantity = QuantityUtils.parse(rs.getString(3));
            NutritionType nutritionType = NutritionType.valueOf(rs.getString(4));

            data.put(id, new Goal(id, goalType, quantity, nutritionType));
            logger.debug("goal " + id + " converted from SQL format, added to map");
        }
        return data;
    }
}
