package org.boothverse.foodpants.business.services;

import lombok.NonNull;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.business.dao.RecipeDAO;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.Recipe;

import java.util.*;

/**
 * service dealing with processing recipes
 */
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

    /**
     * Returns a list of stored recipes.
     *
     * @return the list of stored recipes
     */
    public List<Recipe> getRecipes() {
        return new ArrayList<>(recipes.values());
    }

    /**
     * Gets the recipe with the specified id from the service
     *
     * @param id the id of the desired recipe
     * @return the desired recipe
     */
    public Recipe getRecipe(String id) throws PantsNotFoundException {
        if (!recipes.containsKey(id)) throw new PantsNotFoundException("recipe " + id + " not found");
        return recipes.get(id);
    }

    /**
     * Gets a list of recipes based on the given list of ingredients
     *
     * @param ingredients the given list of ingredients
     * @return a list of recipes
     */
    public List<Recipe> getRecipesByIngredients(List<FoodInstance> ingredients) {
        return recipes.values().stream()
            .sorted(Comparator.comparingDouble(recipe -> {
                long count = recipe.getIngredients().stream().filter(ingredients::contains).count();
                System.out.println(count + " matches");
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
        recipes.put(recipe.getId(), recipe);
        dao.save(recipe);
    }

    /**
     * Modifies the given recipe in the database and service
     *
     * @param recipe the recipe to be modified
     */
    public void editRecipe(Recipe recipe) throws PantsNotFoundException {
        String id = recipe.getId();
        if (!recipes.containsKey(id)) throw new PantsNotFoundException("recipe " + id + " not found");

        recipes.replace(id, recipe);
        dao.save(recipe);
    }

    /**
     * Removes a recipe from the database and service
     *
     * @param id the id of the recipe to be removed
     * @throws PantsNotFoundException
     */
    public void removeRecipe(String id) throws PantsNotFoundException {
        if (!recipes.containsKey(id)) throw new PantsNotFoundException("recipe " + id + " not found");

        recipes.remove(id);
        dao.remove(id);
    }

    /**
     * Get a list of ingredients for the given recipe
     *
     * @param recipeId the given recipe
     * @return the list of ingredients
     */
    public List<FoodInstance> getIngredients(String recipeId) throws PantsNotFoundException {
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
        PantryService pantryService = Services.PANTRY_SERVICE;
        NutritionService nutritionService = Services.NUTRITION_SERVICE;
        Recipe recipe = getRecipe(recipeId);

        // Remove used pantry items
        if (isUsingPantry) {
            for (FoodInstance ingredient : recipe.getIngredients()) {
                try {
                    pantryService.removeItem(ingredient.getId(), ingredient.getQuantity());
                } catch (PantsNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        NutritionInstance consumed = recipe.createNutritionInstance(consumedServings);
        FoodInstance leftover = recipe.createFoodInstance(leftoverServings);

        // Add cooked products to the nutrition log and pantry
        nutritionService.addItem(consumed);
        pantryService.addItem(leftover.getId(), leftover.getQuantity());
    }

    /**
     * Produces a list of recipes for the user
     *
     * @return a list of recipes for the user
     */
    public List<Recipe> getRecommendedRecipes() {
        return getRecipesByIngredients(Services.PANTRY_SERVICE.getItems());
    }

    public void addMissingIngredientsToCart(String recipeId) throws PantsNotFoundException {
        PantryService pantryService = Services.PANTRY_SERVICE;
        ShoppingService shoppingService = Services.SHOPPING_SERVICE;

        List<FoodInstance> missingIngredients = pantryService.getMissing(getIngredients(recipeId));
        shoppingService.addItems(missingIngredients);
    }
}
