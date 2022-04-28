package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ShoppingController implements FoodInstanceController {
    @Override
    public List<FoodInstance> getItems() {
        return Services.SHOPPING_SERVICE.getItems();
    }

    @Override
    public FoodInstance addItem(String foodId, Quantity<?> quantity) {
        return Services.SHOPPING_SERVICE.addItem(foodId, quantity);
    }

    @Override
    public FoodInstance editItem(String foodId, Quantity<?> quantity) {
        return Services.SHOPPING_SERVICE.editItem(foodId, quantity);
    }

    @Override
    public void removeItem(String foodId) {
        Services.SHOPPING_SERVICE.removeItem(foodId);
    }

    public Integer purchaseItems(List<String> foodIds) {
        return null;
    }

    public boolean export(String fileFormat, Path destination) {
        return Services.SHOPPING_SERVICE.export(fileFormat, destination);
    }
}
