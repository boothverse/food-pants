package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.Food;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FoodService {
    protected Map<String, Food> foods;

    /**
     * Loads the foods from the database.
     */
    public FoodService() {

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
        return foods.getOrDefault(id, null);
    }

    /**
     * Adds a food to the service and the database.
     * @param food
     */
    public void addFood(Food food) {

    }

    /**
     * Updates the given food within the service and the database.
     * Should throw a custom exception if the food's ID does not correspond with a food registered
     * in the service.
     *
     * @param food
     */
    public void editFood(Food food) {

    }

    /**
     * Removes the given food from the service and the database.
     * Should throw a custom exception if the food is not found.
     *
     * @param id
     */
    public void removeFood(String id) {

    }

    /**
     * Returns the name of the given food.
     * Should throw a custom exception if the food is not found.
     *
     * @param id
     * @return
     */
    public String getFoodName(String id) {
        return null;
    }

    public String[] getEnumOptions(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
