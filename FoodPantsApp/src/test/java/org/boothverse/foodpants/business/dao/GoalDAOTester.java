package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.NutritionType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalDAOTester {

    @Test
    @Order(1)
    void goalSaveTest() throws IOException {
        ListDAO<Goal> dao = new GoalDAO();

        String id = "cBIYEBLz32qrljn";
        GoalType goalType = GoalType.MAXIMIZE;
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);
        NutritionType nutritionType = NutritionType.CALORIES;

        Goal goal = new Goal(id, goalType, quantity, nutritionType);

        dao.save(goal);
    }

    @Test
    @Order(2)
    void goalLoadTest() throws IOException {
        ListDAO<Goal> dao = new GoalDAO();
        String id = "cBIYEBLz32qrljn";
        GoalType goalType = GoalType.MAXIMIZE;
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);
        NutritionType nutritionType = NutritionType.CALORIES;

        Map<String, Goal> goals = dao.load();
        assertEquals(1, goals.size());

        Goal goal = goals.get(id);

        assertEquals(goal.getGoalType(), goalType);
        assertEquals(goal.getNutritionType(), nutritionType);
        assertEquals(goal.getDailyQuantity().getUnit(), quantity.getUnit());
        assertEquals(goal.getDailyQuantity().getValue(), 50.0);
    }
}
