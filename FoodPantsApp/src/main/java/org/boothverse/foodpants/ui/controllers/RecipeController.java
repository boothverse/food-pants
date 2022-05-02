package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.*;

import java.util.List;

public class RecipeController {
    /**
     * Gets the specified recipe.
     *
     * @param id
     * @return
     * @throws PantsNotFoundException
     */
    public Recipe getRecipe(String id) throws PantsNotFoundException {
        return Services.RECIPE_SERVICE.getRecipe(id);
    }

    /**
     * Gets a list of all the recipes.
     *
     * @return
     */
    public List<Recipe> getRecipes() {
        return Services.RECIPE_SERVICE.getRecipes();
    }

    /**
     * Gets a list of recommended recipes.
     *
     * @return
     */
    public List<Recipe> getRecommendedRecipes() {
        return Services.RECIPE_SERVICE.getRecommendedRecipes();
    }

    /**
     * Gets a list of recipes based on a search term.
     *
     * @param query
     * @return
     */
    public List<Recipe> searchByRecipeName(String query) {
        return Services.RECIPE_SERVICE.getRecipesNameStartsWith(query);
    }

    /**
     * Adds a new recipe to the system.
     *
     * @param basis
     * @param instructions
     * @param servings
     * @return Recipe
     */
    public Recipe addRecipe(Food basis, List<FoodInstance> ingredients, String instructions, Double servings) {

        Recipe recipe = new Recipe(basis.getId(), basis.getName(), basis.getFoodGroup(), basis.getNutrition(),
            instructions, ingredients, servings);
        Services.RECIPE_SERVICE.addRecipe(recipe);

        return recipe;
    }

    /**
     * Modifies an existing recipe with the given info.
     *
     * @param recipe
     * @throws PantsNotFoundException
     */
    public void editRecipe(Recipe recipe) throws PantsNotFoundException {
        Services.RECIPE_SERVICE.editRecipe(recipe);
    }

    /**
     * Adds the ingredients ina  recipe to the shopping list.
     *
     * @param recipeId
     * @throws PantsNotFoundException
     */
    public void addIngredientsToCart(String recipeId) throws PantsNotFoundException {
        List<FoodInstance> ingredients = Services.RECIPE_SERVICE.getIngredients(recipeId);
        Services.SHOPPING_SERVICE.addItems(ingredients);
    }

    /**
     * Adds the ingredients in a recipe which are not in the pantry to the shopping list.
     *
     * @param recipeId
     * @throws PantsNotFoundException
     */
    public void addMissingIngredientsToCart(String recipeId) throws PantsNotFoundException {
        Services.RECIPE_SERVICE.addMissingIngredientsToCart(recipeId);
    }

    /**
     * Creates a food item representing the recipe, creates a nutrition instance if some is consumed and consumes pantry items.
     *
     * @param recipeId
     * @param isUsePantry
     * @param consumedServings
     * @param leftoverServings
     * @throws PantsNotFoundException
     */
    public void produceCookedRecipe(String recipeId, boolean isUsePantry,
                                    Double consumedServings, Double leftoverServings) throws PantsNotFoundException {

        Services.RECIPE_SERVICE.produceCookedRecipe(recipeId, isUsePantry,
            consumedServings, leftoverServings);
    }
}
