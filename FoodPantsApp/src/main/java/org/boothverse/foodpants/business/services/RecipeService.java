package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.Recipe;

import java.util.List;
import java.util.Map;

public class RecipeService {

    protected Map<String, Recipe> recipes;

    /**
     * Loads the recipes from the database.
     */
    public RecipeService() {

    }

    public List<Recipe> getRecipes() {
        return null;
    }

    public Recipe getRecipe(String id) {
        return null;
    }

    public List<Recipe> getRecipesByIngredients(List<FoodInstance> ingredients) {
        return null;
    }

    public List<Recipe> getRecipesNameStartsWith(String query) {
        return null;
    }

    public void addRecipe(Recipe recipe) {

    }

    public void editRecipe(Recipe recipe) {

    }

    public List<FoodInstance> getIngredients(String recipeId) {
        return null;
    }

    public void produceCookedRecipe(String recipeId, Boolean isUsingPantry, Double consumedServings, Double leftoverServings) {

    }

}
