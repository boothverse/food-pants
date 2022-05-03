package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.*;
import org.junit.jupiter.api.*;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeServiceTests {
    static RecipeService service = new RecipeService();
    NutritionDescriptor nd = new NutritionDescriptor();
    List<FoodInstance> ingredients = new ArrayList<>();
    Recipe recipe = new Recipe("dkgjcjad", "Good stuff", FoodGroup.OTHER, nd, "Make", ingredients, 5d);
    static Recipe editedRecipe;

    @BeforeAll
    public static void init(){
        service.dao.removeAll();
        service = new RecipeService();
    }

    @Test
    @Order(1)
    public void getRecipesEmptyTest(){
        Assertions.assertTrue(service.getRecipes().isEmpty());
    }

    @Test
    @Order(2)
    public void getRecipeThrowsTest(){
        Assertions.assertThrows(PantsNotFoundException.class, () -> {service.getRecipe(recipe.getId());});
    }

    @Test
    @Order(3)
    public void addRecipeTest(){
        service.addRecipe(recipe);
        Assertions.assertTrue(service.recipes.containsValue(recipe));

    }

    @Test
    @Order(4)
    public void getRecipeTest(){
        try {
            Assertions.assertTrue(service.getRecipe(recipe.getId()).equals(recipe));
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(5)
    public void editRecipeTest(){
        List<FoodInstance> editedIngredients = new ArrayList<>();
        Food food1 = new Food("kdfkjalnad", "Banana", FoodGroup.FRUIT, nd);
        Food food2 = new Food("fwjadjda", "Apple", FoodGroup.FRUIT, nd);
        Food food3 = new Food("eqvhjdbj", "Orange", FoodGroup.FRUIT, nd);
        editedIngredients.add(food1.createInstance(Quantities.getQuantity(2, Units.KILOGRAM)));
        editedIngredients.add(food2.createInstance(Quantities.getQuantity(3, Units.KILOGRAM)));
        editedRecipe = new Recipe("dkgjcjad", "Good stuff", FoodGroup.OTHER, nd, "Make", editedIngredients, 5d);
        try {
            service.editRecipe(editedRecipe);
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
        Assertions.assertTrue(service.recipes.containsValue(editedRecipe));
    }

    @Test
    @Order(6)
    public void getRecipeByNameMatchingTest(){
        Assertions.assertTrue(service.getRecipesNameStartsWith("Good stuff").contains(editedRecipe));
    }

    @Test
    @Order(7)
    public void getRecipeByNameStartsWithTest(){
        Assertions.assertTrue(service.getRecipesNameStartsWith("Go").contains(editedRecipe));
    }

    @Test
    @Order(8)
    public void getRecipeByNameNoneTest(){
        Assertions.assertTrue(service.getRecipesNameStartsWith("wowza").isEmpty());
    }

    @Test
    @Order(9)
    public void getRecipeByIngredientsTrueTest(){
        List<FoodInstance> editedIngredients = new ArrayList<>();
        Food food1 = new Food("kdfkjalnad", "Banana", FoodGroup.FRUIT, nd);
        Food food2 = new Food("fwjadjda", "Apple", FoodGroup.FRUIT, nd);
        editedIngredients.add(food1.createInstance(Quantities.getQuantity(2, Units.KILOGRAM)));
        editedIngredients.add(food2.createInstance(Quantities.getQuantity(3, Units.KILOGRAM)));
        Assertions.assertTrue(service.getRecipesByIngredients(editedIngredients).contains(editedRecipe));
    }

    @Test
    @Order(10)
    public void getRecipeByIngredientsFalseTest(){
        List<FoodInstance> editedIngredients = new ArrayList<>();
        Food food3 = new Food("eqvhjdbj", "Orange", FoodGroup.FRUIT, nd);
        editedIngredients.add(food3.createInstance(Quantities.getQuantity(2, Units.KILOGRAM)));
        Assertions.assertFalse(service.getRecipesByIngredients(editedIngredients).contains(recipe));
    }

    @Test
    @Order(11)
    public void getRecipeByIngredientsWrongQuantityTest(){
        List<FoodInstance> editedIngredients = new ArrayList<>();
        Food food1 = new Food("kdfkjalnad", "Banana", FoodGroup.FRUIT, nd);
        editedIngredients.add(food1.createInstance(Quantities.getQuantity(1, Units.KILOGRAM)));
        Assertions.assertFalse(service.getRecipesByIngredients(editedIngredients).contains(recipe));
    }

}
