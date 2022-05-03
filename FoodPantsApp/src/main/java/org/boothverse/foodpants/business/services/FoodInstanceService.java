package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.dao.FoodInstanceDAO;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
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
        FoodInstance item;
        if (items.containsKey(id)) {    // add to existing food
            Quantity curQuantity = items.get(id).getQuantity();
            item = new FoodInstance(id, quantity.add(curQuantity));
            items.replace(id, item);
        } else {                        // insert new food
            item = new FoodInstance(id, quantity);
            items.put(id, item);
        }
        dao.save(item);
        return item;
    }

    /**
     * Adds the list of items to the service and the database.
     * If the service already has the given food, just add the specified amount to the service.
     *
     * @param itemsToAdd
     */
    public void addItems(List<FoodInstance> itemsToAdd) {
        itemsToAdd.forEach(itemToAdd -> {
            String id = itemToAdd.getId();
            if (items.containsKey(id)) {    // food already exists in pantry, just add more
                Quantity curQuantity = items.get(id).getQuantity();
                itemToAdd.setQuantity(itemToAdd.getQuantity().add(curQuantity));
            }
            items.put(id, itemToAdd);
            dao.save(itemToAdd);
        });
    }

    /**
     * Edits the quantity of an item being tracked by the service,
     * and updates the database accordingly.
     * Should throw a custom exception if the item is not found.
     *
     * @param id
     * @param quantity
     */
    public FoodInstance editItem(String id, Quantity<?> quantity) throws PantsNotFoundException {
        if (!items.containsKey(id)) throw new PantsNotFoundException("food " + id + " not found");

        FoodInstance item = new FoodInstance(id, quantity);
        items.replace(id, item);
        dao.save(item);
        return item;
    }

    /**
     * Removes the given item from the service and the database.
     * Should throw a custom exception if the item is not found.
     *
     * @param id
     */
    public void removeItem(String id) throws PantsNotFoundException {
        if (!items.containsKey(id)) throw new PantsNotFoundException("food " + id + " not found");

        items.remove(id);
        dao.remove(id);
    }
}
