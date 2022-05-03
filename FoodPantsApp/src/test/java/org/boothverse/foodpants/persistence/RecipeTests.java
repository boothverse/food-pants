package org.boothverse.foodpants.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static tech.units.indriya.AbstractUnit.ONE;

public class RecipeTests {
    NutritionDescriptor nd = new NutritionDescriptor();
    List<FoodInstance> ingredients = new ArrayList<>();
    Recipe recipe = new Recipe("dkgjcjad", "Good stuff", FoodGroup.OTHER, nd, "Make", ingredients, 5d);
    FoodInstance instance = new FoodInstance("dkgjcjad", Quantities.getQuantity(1, ONE));
    @Test
    public void createFoodInstance(){
        Assertions.assertTrue(recipe.createFoodInstance(5d).equals(instance));
    }

    @Test
    public void createFoodInstanceNullServings(){
        Assertions.assertNull(recipe.createFoodInstance(null));
    }

    @Test
    public void createNutritionInstance(){
        NutritionInstance nutritionInstance = recipe.createNutritionInstance(5d);
        Calendar cal = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        cal.setTime(nutritionInstance.getConsumedAt());
        Assertions.assertTrue(nutritionInstance.getFoodId().equals(recipe.getId()));
        Assertions.assertTrue(nutritionInstance.getQuantity().equals(Quantities.getQuantity(1, ONE)));
        Assertions.assertEquals(cal.get(Calendar.DAY_OF_YEAR), today.get(Calendar.DAY_OF_YEAR));
    }

    @Test
    public void createNutritionInstanceNullServings(){
        Assertions.assertNull(recipe.createNutritionInstance(null));
    }
}
