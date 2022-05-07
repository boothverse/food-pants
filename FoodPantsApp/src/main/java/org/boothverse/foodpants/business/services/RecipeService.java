package org.boothverse.foodpants.business.services;

import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.business.dao.RecipeDAO;
import org.boothverse.foodpants.business.services.exceptions.PantsConversionFailedException;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.Recipe;

import java.util.*;

/**
 * service dealing with processing recipes
 */
public class RecipeService {
    private static Logger logger = LogManager.getLogger(RecipeService.class);

    @NonNull
    protected Map<String, Recipe> recipes;
    protected final ListDAO<Recipe> dao = new RecipeDAO();

    /**
     * Loads the recipes from the database.
     */
    public RecipeService() {
        logger.info("Loading recipes from database");
        recipes = dao.load();
    }

    /**
     * Returns a list of stored recipes.
     *
     * @return the list of stored recipes
     */
    public List<Recipe> getRecipes() {
        logger.info("Getting all recipes as list");
        return new ArrayList<>(recipes.values());
    }

    /**
     * Gets the recipe with the specified id from the service
     *
     * @param id the id of the desired recipe
     * @return the desired recipe
     */
    public Recipe getRecipe(String id) throws PantsNotFoundException {
        if (!recipes.containsKey(id)){
            logger.warn("Trying to get a recipe that does not exist with id " + id);
            throw new PantsNotFoundException("recipe " + id + " not found");
        }
        logger.info("Getting recipe with id " + id);
        return recipes.get(id);
    }

    /**
     * Gets a list of recipes based on the given list of ingredients
     *
     * @param ingredients the given list of ingredients
     * @return a list of recipes
     */
    public List<Recipe> getRecipesByIngredients(List<FoodInstance> ingredients) {
        logger.info("Getting recipes sorted by frequency of specific ingredients");
        return recipes.values().stream()
            .sorted(Comparator.comparingDouble(recipe -> {
                long count = recipe.getIngredients().stream().filter(ingredients::contains).count();
                logger.info("Recipe with id " + recipe.getId() + " has " + count + " matches");
                return (double) recipe.getIngredients().size() / count;
            }))
            .limit(recipes.size() / 3 + 1)
            .toList();
    }

    /**
     * Get a list of recipes names based on a given string
     *
     * @param query a given string
     * @return a list of recipes names
     */
    public List<Recipe> getRecipesNameStartsWith(String query) {
        logger.info("Getting recipes that start with " + query);
        return recipes.values().stream()
            .filter(recipe -> recipe.getName().startsWith(query))
            .toList();
    }

    /**
     * Adds a recipe to the service and database
     *
     * @param recipe the recipe to be added
     */
    public void addRecipe(Recipe recipe) {
        logger.info("Adding recipe with id " + recipe.getId());
        recipes.put(recipe.getId(), recipe);
        logger.info("Saving recipe with id " + recipe.getId() + " in database");
        dao.save(recipe);
    }

    /**
     * Modifies the given recipe in the database and service
     *
     * @param recipe the recipe to be modified
     */
    public void editRecipe(Recipe recipe) throws PantsNotFoundException {
        String id = recipe.getId();
        if (!recipes.containsKey(id)){
            logger.warn("Trying to edit a recipe that does not exist with id " + id);
            throw new PantsNotFoundException("recipe " + id + " not found");
        }

        logger.info("Updating recipe with id " + id);
        recipes.replace(id, recipe);
        logger.info("Saving updated recipe with id " + id + " in database");
        dao.save(recipe);
    }

    public void removeRecipe(String id) throws PantsNotFoundException {
        if (!recipes.containsKey(id)){
            logger.warn("Trying to remove a recipe that does not exist with id " + id);
            throw new PantsNotFoundException("recipe " + id + " not found");
        }

        logger.info("Removing recipe with id " + id);
        recipes.remove(id);
        logger.info("Removing recipe with id " + id + " from database");
        dao.remove(id);
    }

    /**
     * Get a list of ingredients for the given recipe
     *
     * @param recipeId the given recipe
     * @return the list of ingredients
     */
    public List<FoodInstance> getIngredients(String recipeId) throws PantsNotFoundException {
        logger.info("Getting list of ingredients from recipe with id " + recipeId);
        return getRecipe(recipeId).getIngredients();
    }

    /**
     * Takes a recipe and logs the recipe in the food log and pantry
     *
     * @param recipeId the id of the recipe
     * @param isUsingPantry whether pantry ingredients are being used
     * @param consumedServings the amount of servings being eaten after the recipe is prepared
     * @param leftoverServings the amount of servings left over after the recipe is made
     */
    public void produceCookedRecipe(String recipeId, Boolean isUsingPantry, Double consumedServings, Double leftoverServings) throws PantsNotFoundException {
        logger.info("Producing recipe with id " + recipeId + " into food item with " + leftoverServings + " leftover and a nutritional item with " + consumedServings + " consumed");
        PantryService pantryService = Services.PANTRY_SERVICE;
        NutritionService nutritionService = Services.NUTRITION_SERVICE;
        Recipe recipe = getRecipe(recipeId);

        // Remove used pantry items
        if (isUsingPantry) {
            for (FoodInstance ingredient : recipe.getIngredients()) {
                try {
                    logger.info("Removing items from pantry used to make recipe with id " + recipeId);
                    pantryService.removeItem(ingredient.getId(), ingredient.getQuantity());
                } catch (PantsNotFoundException e) {
                    logger.error("Ingredient used to make recipe with id " + recipeId + " not found in pantry");
                } catch (PantsConversionFailedException e) {
                    logger.error("Ingredient used to make recipe with id " + recipeId + " could not get removed from the pantry");
                }
            }
        }

        logger.info("Making nutrition instance with " + consumedServings + " consumed servings of recipe with id " + recipeId);
        NutritionInstance consumed = recipe.createNutritionInstance(consumedServings);
        logger.info("Making food instance with " + leftoverServings + " leftover servings of recipe with id " + recipeId);
        FoodInstance leftover = recipe.createFoodInstance(leftoverServings);

        // Add cooked products to the nutrition log and pantry
        logger.info("Adding nutrition instance with id " + consumed.getId() + " to nutrition log");
        nutritionService.addItem(consumed);
        logger.info("Adding food instance with id " + leftover.getId() + " and quantity " + leftover.getQuantity() + " to pantry");
        try {
            pantryService.addItem(leftover.getId(), leftover.getQuantity());
        } catch (PantsConversionFailedException e) {
            logger.error("Could not add recipe to pantry... bad conversion, bad! :(");
        }
    }

    /**
     * Produces a list of recipes for the user
     *
     * @return a list of recipes for the user
     */
    public List<Recipe> getRecommendedRecipes() {
        logger.info("Getting recipes with highest frequency of ingredients in pantry");
        return getRecipesByIngredients(Services.PANTRY_SERVICE.getItems());
    }

    /**
     * Takes a recipe and adds ingredients not in pantry to shopping list
     *
     * @param recipeId the id of the recipe
     */
    public void addMissingIngredientsToCart(String recipeId) throws PantsNotFoundException {
        PantryService pantryService = Services.PANTRY_SERVICE;
        ShoppingService shoppingService = Services.SHOPPING_SERVICE;

        logger.info("Getting ingredients from recipe with id " + recipeId + " not in pantry");
        List<FoodInstance> missingIngredients = pantryService.getMissing(getIngredients(recipeId));
        logger.info("Adding missing ingredients from recipe with id " + recipeId + " to shopping list");
        shoppingService.addItems(missingIngredients);
    }
}
