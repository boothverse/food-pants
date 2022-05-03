package org.boothverse.foodpants.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

public class GoalTests {
    Quantity<Mass> quantity = Quantities.getQuantity(5, Units.KILOGRAM);
    Quantity<Mass> lowQuantity = Quantities.getQuantity(4, Units.KILOGRAM);
    Quantity<Mass> highQuantity = Quantities.getQuantity(6, Units.KILOGRAM);
    Goal<Mass> maxGoal = new Goal<>("1", GoalType.MAXIMIZE, quantity, NutritionType.CALORIES);
    Goal<Mass> minGoal = new Goal<>("1", GoalType.MINIMIZE, quantity, NutritionType.CALORIES);
    @Test
    public void checkGoalAttainedMaximize(){
        Assertions.assertTrue(maxGoal.isAttained(lowQuantity));
    }

    @Test
    public void checkGoalAttainedMinimize(){
        Assertions.assertTrue(minGoal.isAttained(highQuantity));
    }

    @Test
    public void checkGoalNotAttainedMaximize(){
        Assertions.assertFalse(maxGoal.isAttained(highQuantity));
    }

    @Test
    public void checkGoalNotAttainedMinimize(){
        Assertions.assertFalse(minGoal.isAttained(lowQuantity));
    }

    @Test
    public void checkGoalAttainedNullQuantity(){
        minGoal.isAttained(null);
    }
}
