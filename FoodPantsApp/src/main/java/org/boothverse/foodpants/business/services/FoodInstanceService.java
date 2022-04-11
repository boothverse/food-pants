package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.List;
import java.util.Map;

public abstract class FoodInstanceService {
    protected Map<String, FoodInstance> items;

    /**
     * Loads the items from the database.
     *
     * @param dbFilename
     */
    public FoodInstanceService(String dbFilename) {

    }

    /**
     * Returns all items tracked by the service.
     *
     * @return
     */
    public List<FoodInstance> getItems() {
        return null;
    }

    /**
     * Adds an item to the service and the database.
     *
     * @param id
     * @param quantity
     */
    public void addItem(String id, Quantity<?> quantity) {

    }

    /**
     * Edits the quantity of an item being tracked by the service,
     * and updates the database accordingly.
     * Should throw a custom exception if the item is not found.
     *
     * @param id
     * @param quantity
     */
    public void editItem(String id, Quantity<?> quantity) {

    }

    /**
     * Removes the given item from the service and the database.
     * Should throw a custom exception if the item is not found.
     *
     * @param id
     */
    public void removeItem(String id) {

    }
}
