package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionDescriptor;
import org.boothverse.foodpants.persistence.Recipe;

import java.util.List;
import java.util.Map;

public class RecipeController {
    public Recipe getRecipe(String id) {
        return Services.RECIPE_SERVICE.getRecipe(id);
    }

    public List<Recipe> getRecipes() {
        return Services.RECIPE_SERVICE.getRecipes();
    }

    public List<Recipe> getRecommendedRecipes() {
        return Services.RECIPE_SERVICE.getRecommendedRecipes();
    }

    public List<Recipe> searchByRecipeName(String query) {
        return Services.RECIPE_SERVICE.getRecipesNameStartsWith(query);
    }

    public Recipe addRecipe(String name, FoodGroup foodGroup, List<FoodInstance> ingredients,
                            NutritionDescriptor nutrition, String instructions, Double servings) {

        Recipe recipe = new Recipe(Services.ID_SERVICE.getId(), name, foodGroup, nutrition,
            instructions, ingredients, servings);
        Services.RECIPE_SERVICE.addRecipe(recipe);

        return recipe;
    }

    public void editRecipe(Recipe recipe) {
        Services.RECIPE_SERVICE.editRecipe(recipe);
    }

    public void addIngredientsToCart(String recipeId) {
        Recipe recipe = Services.RECIPE_SERVICE.getRecipe(recipeId);

        List<FoodInstance> ingredients = recipe.getIngredients();
        Services.SHOPPING_SERVICE.addItems(ingredients);
    }

    public void addMissingIngredientsToCart(String recipeId) {
        // TODO: this
        // I think the recipe service is missing something guys :0
    }

    public void produceCookedRecipe(String recipeId, boolean isUsePantry,
                                    Double consumedServings, Double leftoverServings) {

        Services.RECIPE_SERVICE.produceCookedRecipe(recipeId, isUsePantry,
            consumedServings, leftoverServings);
    }
}
