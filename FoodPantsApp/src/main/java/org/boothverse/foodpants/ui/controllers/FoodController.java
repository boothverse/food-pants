package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionDescriptor;

import java.util.List;

public class FoodController {
    /**
     *
     * @param id
     * @return
     */
    public Food getFood(String id) throws PantsNotFoundException {
        return Services.FOOD_SERVICE.getFood(id);
    }

    /**
     *
     * @param name
     * @param category
     * @param nutrition
     * @return
     */
    public Food addFood(String name, FoodGroup category, NutritionDescriptor nutrition) {
        Food food = new Food(Services.ID_SERVICE.getId(), name, category, nutrition);
        Services.FOOD_SERVICE.addFood(food);
        return food;
    }

    /**
     *
     * @param id
     * @param name
     * @param category
     * @param nutrition
     * @return
     */
    public Food editFood(String id, String name, FoodGroup category, NutritionDescriptor nutrition) throws PantsNotFoundException{
        Food food = new Food(id, name, category, nutrition);
        Services.FOOD_SERVICE.editFood(food);
        return food;
    }

    /**
     *
     * @param id
     */
    public void removeFood(String id) throws PantsNotFoundException {
        Services.FOOD_SERVICE.removeFood(id);
    }

    /**
     *
     * @return
     */
    public List<Food> getFoods() {
        return Services.FOOD_SERVICE.getFoods();
    }
}
