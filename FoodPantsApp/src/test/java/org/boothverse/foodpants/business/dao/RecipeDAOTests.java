package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.*;
import org.junit.jupiter.api.*;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeDAOTests {
    static ListDAO<Recipe> dao = new RecipeDAO();

    @AfterAll
    static void clear(){
        dao.removeAll();
    }

    @Test
    @Order(1)
    void recipeSaveTest() throws IOException {

        String id = "aebfilnjefapin34quv", name = "Yummy Soup";
        FoodGroup foodGroup = FoodGroup.OTHER;
        Map<NutritionType, Quantity<?>> nutritionDescriptorMap = new LinkedHashMap<>();
        nutritionDescriptorMap.put(NutritionType.CALORIES, Quantities.getQuantity(4, Units.GRAM));
        NutritionDescriptor nutrition = new NutritionDescriptor(nutritionDescriptorMap, Quantities.getQuantity(6, Units.GRAM));
        String instructions = "Mix together the ingredients. Duh.";
        FoodInstance rice = new FoodInstance("1qrfdfsglzsdf", Quantities.getQuantity(5, Units.GRAM));
        List<FoodInstance> ingredients = new ArrayList<>();
        ingredients.add(rice);
        double servings = 3;

        Recipe recipe = new Recipe(id, name, foodGroup, nutrition, instructions, ingredients, servings);

        dao.save(recipe);
    }

    @Test
    @Order(2)
    void recipeLoadTest() throws IOException {
        String id = "aebfilnjefapin34quv", name = "Yummy Soup";
        FoodGroup foodGroup = FoodGroup.OTHER;
        Map<NutritionType, Quantity<?>> nutritionDescriptorMap = new LinkedHashMap<>();
        nutritionDescriptorMap.put(NutritionType.CALORIES, Quantities.getQuantity(4, Units.GRAM));
        NutritionDescriptor nutrition = new NutritionDescriptor(nutritionDescriptorMap, Quantities.getQuantity(6, Units.GRAM));
        String instructions = "Mix together the ingredients. Duh.";
        FoodInstance rice = new FoodInstance("1qrfdfsglzsdf", Quantities.getQuantity(5, Units.GRAM));
        List<FoodInstance> ingredients = new ArrayList<>();
        ingredients.add(rice);
        double servings = 3;

        Map<String, Recipe> recipes = dao.load();
        assertEquals(1, recipes.size());

        Recipe recipe = recipes.get(id);

        assertEquals(recipe.getName(), name);
        assertEquals(recipe.getFoodGroup(), foodGroup);
        assertEquals(recipe.getNutrition(), nutrition);
        assertEquals(recipe.getInstructions(), instructions);
        assertEquals(recipe.getServings(), servings);
    }
}
