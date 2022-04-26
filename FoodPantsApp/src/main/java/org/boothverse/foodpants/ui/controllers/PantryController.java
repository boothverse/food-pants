package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.List;
import java.util.Map;

public class PantryController implements FoodInstanceController {
    @Override
    public List<FoodInstance> getItems() {
        return null;
    }

    @Override
    public FoodInstance addItem(String foodId, Quantity<?> quantity) {
        return null;
    }

    @Override
    public FoodInstance editItem(String foodId, Quantity<?> quantity) {
        return null;
    }

    @Override
    public FoodInstance removeItem(String foodId) {
        return null;
    }

    public FoodInstance consume(String foodId, Quantity<?> quantity) {
        return null;
    }

    public Map<String, FoodInstance> searchByFoodName(String query) {
        return null;
    }
}
