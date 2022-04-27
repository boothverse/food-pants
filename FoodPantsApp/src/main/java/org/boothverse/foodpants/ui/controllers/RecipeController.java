package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.Recipe;

import java.util.List;
import java.util.Map;

public class RecipeController {
    public Recipe getRecipe(String id) {
        return null;
    }

    public List<Recipe> getRecipes(String id) {
        return null;
    }

    public List<Recipe> getRecommendedRecipes() { return null; }

    public List<Recipe> searchByRecipeName(String query) { return null; }

    public Recipe addRecipe(String name, List<FoodInstance> ingredients, String instructions, Double servings) { return null; }

    public Recipe editRecipe(String id, Recipe recipe) { return null; }

    public void addIngredientsToCart(String recipeId) {  }

    public void addMissingIngredientsToCart(String recipeId) {  }

    public void produceCookedRecipe(String recipeId, boolean isUsePantry, Double consumedServings, Double leftoverServings) { }

}
