package org.boothverse.foodpants.business.services;

import lombok.NonNull;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.business.dao.RecipeDAO;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.Recipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RecipeService {

    @NonNull
    protected Map<String, Recipe> recipes;
    protected final ListDAO<Recipe> dao = new RecipeDAO();

    /**
     * Loads the recipes from the database.
     */
    public RecipeService() {
        recipes = dao.load();
    }

    public List<Recipe> getRecipes() {
        return new ArrayList<>(recipes.values());
    }

    public Recipe getRecipe(String id) {
        return recipes.get(id);
    }

    public List<Recipe> getRecipesByIngredients(List<FoodInstance> ingredients) {
        return recipes.values().stream()
            .sorted(Comparator.comparingDouble(recipe -> {
                long count = recipe.getIngredients().stream().filter(ingredients::contains).count();
                return (double) count / recipe.getIngredients().size();
            }))
            .toList();
    }

    public List<Recipe> getRecipesNameStartsWith(String query) {
        return recipes.values().stream()
            .filter(recipe -> recipe.getName().startsWith(query))
            .toList();
    }

    public void addRecipe(Recipe recipe) {
        recipes.put(recipe.getId(), recipe);
        dao.save(recipe);
    }

    public void editRecipe(Recipe recipe) {
        recipes.put(recipe.getId(), recipe);
        dao.save(recipe);
    }

    public List<FoodInstance> getIngredients(String recipeId) {
        return recipes.get(recipeId).getIngredients();
    }

    public void produceCookedRecipe(String recipeId, Boolean isUsingPantry, Double consumedServings, Double leftoverServings) {
        PantryService pantryService = Services.PANTRY_SERVICE;
        NutritionService nutritionService = Services.NUTRITION_SERVICE;
        Recipe recipe = recipes.get(recipeId);

        // Remove used pantry items
        if (isUsingPantry) {
            for (FoodInstance ingredient : recipe.getIngredients()) {
                pantryService.removeItem(ingredient.getId(), ingredient.getQuantity());
            }
        }

        NutritionInstance consumed = recipe.createNutritionInstance(consumedServings);
        FoodInstance leftover = recipe.createFoodInstance(leftoverServings);

        // Add cooked products to the nutrition log and pantry
        nutritionService.addItem(consumed);
        pantryService.addItem(leftover.getId(), leftover.getQuantity());
    }

    public List<Recipe> getRecommendedRecipes() {
        return getRecipesByIngredients(Services.PANTRY_SERVICE.getItems());
    }
}
