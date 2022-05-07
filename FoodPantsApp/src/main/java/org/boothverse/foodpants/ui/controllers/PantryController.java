package org.boothverse.foodpants.ui.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.services.PantryService;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsConversionFailedException;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionInstance;

import javax.measure.Quantity;
import java.util.Date;
import java.util.List;

/**
 * A controller which handles user events related to the pantry
 */
public class PantryController implements FoodInstanceController {
    private static Logger logger = LogManager.getLogger(PantryController.class);
    /**
     * Returns a list of all food instances.
     *
     * @return a list of all pantry items
     */
    @Override
    public List<FoodInstance> getItems() {
        PantryService pantryService = Services.PANTRY_SERVICE;
        logger.debug("items retrieved from pantry");
        return Services.PANTRY_SERVICE.getItems();
    }

    /**
     * Adds a food instance to the system.
     *
     * @param foodId the id of the food instance
     * @param quantity the quantity of the food instance
     * @return the newly created food instance
     */
    @Override
    public FoodInstance addItem(String foodId, Quantity<?> quantity) throws PantsConversionFailedException {
        logger.info(foodId + " added to pantry");
        return Services.PANTRY_SERVICE.addItem(foodId, quantity);
    }

    /**
     * Modifies the specified food instance with the given info.
     *
     * @param foodId the id of the food instance
     * @param quantity the quantity of the food instance
     * @return the modified food instance
     * @throws PantsNotFoundException
     */
    @Override
    public FoodInstance editItem(String foodId, Quantity<?> quantity) throws PantsNotFoundException {
        logger.info(foodId + " food item edited in pantry");
        return Services.PANTRY_SERVICE.editItem(foodId, quantity);
    }

    /**
     * Removes the specified food instance.
     *
     * @param foodId the id of the food
     */
    @Override
    public void removeItem(String foodId) throws PantsNotFoundException {
        Services.PANTRY_SERVICE.removeItem(foodId);
        logger.info(foodId + " food item removed from pantry");
    }

    /**
     * Removes the specified food instance and generates a nutrition instance
     *
     * @param foodId the id of the food
     * @param quantity the quantity to be consumed
     */
    public void consume(String foodId, Quantity<?> quantity) throws PantsNotFoundException, PantsConversionFailedException {
        Services.PANTRY_SERVICE.removeItem(foodId, quantity);

        NutritionInstance nutInstance = new NutritionInstance(Services.ID_SERVICE.getId(),
            foodId, quantity, new Date());
        Services.NUTRITION_SERVICE.addItem(nutInstance);
        logger.info(foodId + " item consumed");
    }

    /**
     * Returns a list of food instances given a specific name.
     *
     * @param query a string being used as a search key
     * @return a list of foods related to the search key
     */
    public List<FoodInstance> searchByFoodName(String query) {
        logger.info(query + " item searched in pantry");
        return Services.PANTRY_SERVICE.searchByFoodName(query);
    }
}
