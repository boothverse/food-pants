package org.boothverse.foodpants.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

public class FoodTests {
    NutritionDescriptor nd = new NutritionDescriptor();
    Food food = new Food("1", "1", FoodGroup.FRUIT, nd);

    @Test
    public void createFoodWithEmptyNutritionDescriptor(){
        Assertions.assertEquals(0,food.getNutrition().getNutritionInfo().size());
        Assertions.assertNull(food.getNutrition().getServingSize());
    }

    @Test
    public void createInstanceWithNullQuantity(){
        FoodInstance instance = food.createInstance(null);
        Assertions.assertNull(instance.getQuantity());
    }

    @Test
    public void createInstance(){
        Quantity<Mass> quantity = Quantities.getQuantity(5, Units.KILOGRAM);
        FoodInstance instance = food.createInstance(quantity);
        Assertions.assertEquals(5, instance.getQuantity().getValue());
    }
}
