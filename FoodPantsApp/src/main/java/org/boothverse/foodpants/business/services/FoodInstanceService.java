package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.dao.FoodInstanceDAO;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class FoodInstanceService {
    protected Map<String, FoodInstance> items;
    protected final ListDAO<FoodInstance> dao;

    /**
     * Loads the items from the database.
     *
     * @param dbName
     */
    public FoodInstanceService(String dbName) {
        dao = new FoodInstanceDAO(dbName);
        items = dao.load();
    }

    /**
     * Returns all items tracked by the service.
     *
     * @return the items tracked by the service
     */
    public List<FoodInstance> getItems() {
        return new ArrayList<>(items.values());
    }

    /**
     * Adds an item to the service and the database.
     *
     * @param id
     * @param quantity
     * @return the newly created item
     */
    public FoodInstance addItem(String id, Quantity<?> quantity) {
        FoodInstance item = new FoodInstance(id, quantity);
        items.put(id, item);
        dao.save(item);
        return item;
    }

    /**
     * Edits the quantity of an item being tracked by the service,
     * and updates the database accordingly.
     * Should throw a custom exception if the item is not found.
     *
     * @param id
     * @param quantity
     */
    public FoodInstance editItem(String id, Quantity<?> quantity) {
        // TODO: throw custom exception if the item is not found
        FoodInstance item = new FoodInstance(id, quantity);
        items.put(id, item);
        dao.save(item);
        return item;
    }

    /**
     * Removes the given item from the service and the database.
     * Should throw a custom exception if the item is not found.
     *
     * @param id
     */
    public void removeItem(String id) {
        items.remove(id);
    }
}
