package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsExportShoppingListException;
import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.nio.file.Path;
import java.util.List;

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
        List<FoodInstance> items = Services.SHOPPING_SERVICE.getItems();
        Services.SHOPPING_SERVICE.removeItems(foodIds);
        Services.PANTRY_SERVICE.addItems(items);

        return items.size();
    }

    public void export(Path destination) {
        // TODO: maybe don't try catch here???
        try {
            Services.SHOPPING_SERVICE.export(destination);
        } catch (PantsExportShoppingListException e) {
            e.printStackTrace();
        }
    }
}
