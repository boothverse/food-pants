package org.boothverse.foodpants.business.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.FoodDAO;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.business.services.util.EnumUtils;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;

import java.util.List;
import java.util.Map;

public class FoodService {
    private static Logger logger = LogManager.getLogger(FoodService.class);
    protected Map<String, Food> foods;
    protected final ListDAO<Food> dao = new FoodDAO();

    /**
     * Loads the foods from the database.
     */
    public FoodService() {
        logger.info("Loading foods from database");
        foods = dao.load();
    }

    /**
     * Returns all foods tracked by the service.
     *
     * @return
     */
    public List<Food> getFoods() {
        logger.info("Getting all foods as list");
        return foods.values().stream().toList();
    }

    /**
     * Returns the food of the given id.
     *
     * @param id
     * @return
     */
    public Food getFood(String id) throws PantsNotFoundException {
        if (!foods.containsKey(id)){
            logger.warn("Trying to get a food that does not exist with id " + id);
            throw new PantsNotFoundException("food " + id + " not found");
        }
        logger.info("Getting food with id " + id);
        return foods.get(id);
    }

    /**
     * Adds a food to the service and the database.
     * @param food
     */
    public void addFood(Food food) {
        logger.info("Adding food with id " + food.getId());
        foods.put(food.getId(), food);
        logger.info("Saving food with id " + food.getId() + " in database");
        dao.save(food);
    }

    /**
     * Updates the given food within the service and the database.
     * Should throw a custom exception if the food's ID does not correspond with a food registered
     * in the service.
     *
     * @param food
     */
    public void editFood(Food food) throws PantsNotFoundException {
        String id = food.getId();
        if (!foods.containsKey(id)){
            logger.warn("Trying to edit a food that does not exist with id " + id);
            throw new PantsNotFoundException("food " + id + " not found");
        }
        logger.info("Updating food with id " + id);
        foods.replace(id, food);
        logger.info("Saving updated food with id " + id + " in database");
        dao.save(food);
    }

    /**
     * Removes the given food from the service and the database.
     * Should throw a custom exception if the food is not found.
     *
     * @param id
     */
    public void removeFood(String id) throws PantsNotFoundException {
        if (!foods.containsKey(id)){
            logger.warn("Trying to remove a food that does not exist with id " + id);
            throw new PantsNotFoundException("food " + id + " not found");
        }

        logger.info("Removing food with id " + id);
        foods.remove(id);
        logger.info("Removing food with id " + id + " from database");
        dao.remove(id);
    }

    /**
     * Returns the name of the given food.
     * Should throw a custom exception if the food is not found.
     *
     * @param id
     * @return
     */
    public String getFoodName(String id) throws PantsNotFoundException {
        logger.info("Getting food name with id " + id);
        return getFood(id).getName();
    }

    /**
     * Return a list of food groups.
     *
     * @return
     */
    public String[] getFoodGroups() {
        logger.info("Getting list of food groups");
        return EnumUtils.getEnumOptions(FoodGroup.class);
    }
}
