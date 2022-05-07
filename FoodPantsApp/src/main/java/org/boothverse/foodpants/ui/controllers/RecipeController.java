package org.boothverse.foodpants.ui.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.services.PantryService;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.*;

import java.util.List;

public class RecipeController {
    private static Logger logger = LogManager.getLogger(RecipeController.class);
    /**
     * Gets the specified recipe.
     *
     * @param id
     * @return
     * @throws PantsNotFoundException
     */
    public Recipe getRecipe(String id) throws PantsNotFoundException {
        logger.debug(id + " recipe retrieved");
        return Services.RECIPE_SERVICE.getRecipe(id);
    }

    /**
     * Gets a list of all the recipes.
     *
     * @return
     */
    public List<Recipe> getRecipes() {
        logger.debug("list of recipes retried");
        return Services.RECIPE_SERVICE.getRecipes();
    }

    /**
     * Gets a list of recommended recipes.
     *
     * @return
     */
    public List<Recipe> getRecommendedRecipes() {
        logger.debug("list of recommended recipes retrieved");
        return Services.RECIPE_SERVICE.getRecommendedRecipes();
    }

    /**
     * Gets a list of recipes based on a search term.
     *
     * @param query
     * @return
     */
    public List<Recipe> searchByRecipeName(String query) {
        logger.debug(query + " searched in recipes");
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

        logger.info(recipe.getId() + " added to recipe list");
        return recipe;
    }

    /**
     * Modifies an existing recipe with the given info.
     *
     * @param recipe
     * @throws PantsNotFoundException
     */
    public void editRecipe(Recipe recipe) throws PantsNotFoundException {
        logger.info(recipe.getId() + " recipe updated");
        Services.RECIPE_SERVICE.editRecipe(recipe);
    }

    public void removeRecipe(String id) throws PantsNotFoundException {
        logger.info(id + " recipe removed");
        Services.RECIPE_SERVICE.removeRecipe(id);
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
        logger.info(recipeId + " recipe items added to cart");
    }

    /**
     * Adds the ingredients in a recipe which are not in the pantry to the shopping list.
     *
     * @param recipeId
     * @throws PantsNotFoundException
     */
    public void addMissingIngredientsToCart(String recipeId) throws PantsNotFoundException {
        Services.RECIPE_SERVICE.addMissingIngredientsToCart(recipeId);
        logger.info(" issing items from " + recipeId + " added to cart");
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
        logger.info(recipeId +  " recipe cooked");
    }

    public List<FoodInstance> getMissingItems(Recipe recipe) {
        PantryService p =  new PantryService();
        logger.info(recipe.getId() + " missing items added to cart from recipe");
        return p.getMissing(recipe.getIngredients());
    }

    public void addItemsToPantry(List<FoodInstance> itemsToAdd) {
        PantryService p = new PantryService();
        p.addItems(itemsToAdd);
        logger.info("items added to pantry");
    }
}
