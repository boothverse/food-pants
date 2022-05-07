package org.boothverse.foodpants.business.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.FoodInstanceDAO;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.Recipe;

import javax.measure.Quantity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class FoodInstanceService {
    private static Logger logger = LogManager.getLogger(FoodInstanceService.class);
    protected Map<String, FoodInstance> items;
    protected final ListDAO<FoodInstance> dao;

    /**
     * Loads the items from the database.
     *
     * @param dbName
     */
    public FoodInstanceService(String dbName) {
        dao = new FoodInstanceDAO(dbName);
        logger.info("Loading food instances from " + dbName);
        items = dao.load();
    }

    /**
     * Returns all items tracked by the service.
     *
     * @return the items tracked by the service
     */
    public List<FoodInstance> getItems() {
        logger.info("Getting all food instances as list");
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
            logger.info("Updating item with id " + id + " by adding new quantity " + quantity);
            Quantity curQuantity = items.get(id).getQuantity();
            item = new FoodInstance(id, quantity.add(curQuantity));
            items.replace(id, item);
        } else {                        // insert new food
            logger.info("Inserting new item with id " + id + " and quantity " + quantity);
            item = new FoodInstance(id, quantity);
            items.put(id, item);
        }
        logger.info("Saving item with id " + id + " in database");
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
                logger.info("Updating item with id " + id + " by adding new quantity " + itemToAdd.getQuantity());
                itemToAdd.setQuantity(itemToAdd.getQuantity().add(curQuantity));
            }
            logger.info("Inserting new item with id " + id + " and quantity " + itemToAdd.getQuantity());
            items.put(id, itemToAdd);
            logger.info("Saving item with id " + id + " in database");
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
        if (!items.containsKey(id)){
            logger.warn("Trying to edit a food instance that does not exist with id " + id);
            throw new PantsNotFoundException("food " + id + " not found");
        }
        logger.info("Updating item with id " + id + " by setting new quantity " + quantity);
        FoodInstance item = new FoodInstance(id, quantity);
        items.replace(id, item);
        logger.info("Saving updated item with id " + id + " in database");
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
        if (!items.containsKey(id)){
            logger.warn("Trying to remove a food instance that does not exist with id " + id);
            throw new PantsNotFoundException("food " + id + " not found");
        }

        logger.info("Removing item with id " + id);
        items.remove(id);
        logger.info("Removing item with id " + id + " from database");
        dao.remove(id);
    }
}
