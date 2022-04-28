package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionInstance;

import javax.measure.Quantity;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PantryController implements FoodInstanceController {
    /**
     *
     * @return
     */
    @Override
    public List<FoodInstance> getItems() {
        return Services.PANTRY_SERVICE.getItems();
    }

    /**
     *
     * @param foodId
     * @param quantity
     * @return
     */
    @Override
    public FoodInstance addItem(String foodId, Quantity<?> quantity) {
        return Services.PANTRY_SERVICE.addItem(foodId, quantity);
    }

    /**
     *
     * @param foodId
     * @param quantity
     * @return
     */
    @Override
    public FoodInstance editItem(String foodId, Quantity<?> quantity) {
        return Services.PANTRY_SERVICE.editItem(foodId, quantity);
    }

    /**
     *
     * @param foodId
     */
    @Override
    public void removeItem(String foodId) {
        Services.PANTRY_SERVICE.removeItem(foodId);
    }

    /**
     *
     * @param foodId
     * @param quantity
     */
    public void consume(String foodId, Quantity<?> quantity) {
        Services.PANTRY_SERVICE.removeItem(foodId, quantity);

        NutritionInstance nutInstance = new NutritionInstance(Services.ID_SERVICE.getId(),
            foodId, quantity, new Date());
        Services.NUTRITION_SERVICE.addItem(nutInstance);
    }

    /**
     *
     * @param query
     * @return
     */
    public Map<String, FoodInstance> searchByFoodName(String query) {
        return Services.PANTRY_SERVICE.searchByFoodName(query);
    }
}
