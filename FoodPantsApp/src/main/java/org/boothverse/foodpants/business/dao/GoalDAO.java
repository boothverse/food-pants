package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.Goal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class GoalDAO extends JDBCListDAO<Goal> {
    GoalDAO() {
        super("goals", new String[]{"id", "goalType", "dailyQuantity", "nutritionType"});
    }

    @Override
    protected String[] objToSQL(Goal data) {
        // TODO
        return new String[0];
    }

    @Override
    protected Map<String, Goal> SQLToObj(ResultSet rs) throws SQLException {
        // TODO
        return null;
    }
}
