package org.boothverse.foodpants.ui.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionDescriptor;

import java.util.List;

/**
 * A controller which handles user events related to food
 */
public class FoodController {

    private static Logger logger = LogManager.getLogger(FoodController.class);
    /**
     * Gets the food with the specified id
     *
     * @param id the id of the desired food
     * @return the desired food
     */
    public Food getFood(String id) throws PantsNotFoundException {
        logger.info("getting food " + id);
        return Services.FOOD_SERVICE.getFood(id);
    }

    /**
     * Adds a new food item to the system
     *
     * @param name the name of the food
     * @param category the foods category
     * @param nutrition the nutritional info of the food
     * @return the newly created food
     */
    public Food addFood(String name, FoodGroup category, NutritionDescriptor nutrition) {
        logger.info("adding food called " + name + " of type " + category
            + " with serving size=" + nutrition.getServingSize());
        Food food = new Food(Services.ID_SERVICE.getId(), name, category, nutrition);
        Services.FOOD_SERVICE.addFood(food);
        return food;
    }

    /**
     * Modifies the specified food item with the specified information
     *
     * @param id the id of the food
     * @param name the name of the food
     * @param category the category of the food
     * @param nutrition the nutritional info of the food
     * @return the modified food
     * @throws PantsNotFoundException
     */
    public Food editFood(String id, String name, FoodGroup category, NutritionDescriptor nutrition) throws PantsNotFoundException{
        logger.info("editing food " + id + " (now called " + name + ")");
        Food food = new Food(id, name, category, nutrition);
        Services.FOOD_SERVICE.editFood(food);
        return food;
    }

    /**
     * Removes the specified food item
     *
     * @param id the id of the food to be removed
     */
    public void removeFood(String id) throws PantsNotFoundException {
        logger.info("removing food " + id);
        Services.FOOD_SERVICE.removeFood(id);
    }

    /**
     * Gets a list of all food items
     *
     * @return a list of the foods in the database/service
     */
    public List<Food> getFoods() {
        logger.info("getting all foods");
        List<Food> foods = Services.FOOD_SERVICE.getFoods();
        logger.info("got " + foods.size() + " foods");

        return foods;
    }
}
