package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.List;

/**
 * An abstract controller managing food instances which is used by other controllers
 */
public interface FoodInstanceController {
    /**
     * Returns a list of all food instances.
     *
     * @return a list of all food instances
     */
    public List<FoodInstance> getItems();

    /**
     * Adds a food instance to the system.
     *
     * @param foodId the id of the food instance
     * @param quantity the quantity of the food instance
     * @return the newly created food instance
     */
    public FoodInstance addItem(String foodId, Quantity<?> quantity);

    /**
     * Modifies the specified food instance with the given info.
     *
     * @param foodId the id of the food instance
     * @param quantity the quantity of the food instance
     * @return the modified food instance
     * @throws PantsNotFoundException
     */
    public FoodInstance editItem(String foodId, Quantity<?> quantity) throws PantsNotFoundException;

    /**
     * Removes the specified food instance.
     *
     * @param foodId the id of the food
     */
    public void removeItem(String foodId) throws PantsNotFoundException;
}
