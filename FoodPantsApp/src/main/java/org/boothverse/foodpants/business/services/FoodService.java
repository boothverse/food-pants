package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.dao.FoodDAO;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.persistence.Food;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FoodService {
    protected Map<String, Food> foods;
    protected ListDAO<Food> dao = new FoodDAO();

    /**
     * Loads the foods from the database.
     */
    public FoodService() {
        foods = dao.load();
    }

    /**
     * Returns all foods tracked by the service.
     *
     * @return
     */
    public List<Food> getFoods() {
        return foods.values().stream().toList();
    }

    /**
     * Returns the food of the given id.
     *
     * @param id
     * @return
     */
    public Food getFood(String id) {
        // TODO: throw custom exception if not found
        return foods.get(id);
    }

    /**
     * Adds a food to the service and the database.
     * @param food
     */
    public void addFood(Food food) {
        foods.put(food.getId(), food);
        dao.save(food);
    }

    /**
     * Updates the given food within the service and the database.
     * Should throw a custom exception if the food's ID does not correspond with a food registered
     * in the service.
     *
     * @param food
     */
    public void editFood(Food food) {
        // TODO: throw custom exception if not found
        foods.put(food.getId(), food);
        dao.save(food);
    }

    /**
     * Removes the given food from the service and the database.
     * Should throw a custom exception if the food is not found.
     *
     * @param id
     */
    public void removeFood(String id) {
        foods.remove(id);
        dao.remove(id);
    }

    /**
     * Returns the name of the given food.
     * Should throw a custom exception if the food is not found.
     *
     * @param id
     * @return
     */
    public String getFoodName(String id) {
        // TODO: throw custom exception if not found
        return foods.get(id).getName();
    }

    public String[] getEnumOptions(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
