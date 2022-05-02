package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsExportShoppingListException;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
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
    public FoodInstance editItem(String foodId, Quantity<?> quantity) throws PantsNotFoundException {
        return Services.SHOPPING_SERVICE.editItem(foodId, quantity);
    }

    @Override
    public void removeItem(String foodId) throws PantsNotFoundException {
        Services.SHOPPING_SERVICE.removeItem(foodId);
    }

    public Integer purchaseItems(List<String> foodIds) throws PantsNotFoundException {
        List<FoodInstance> items = Services.SHOPPING_SERVICE.getItems();
        Services.SHOPPING_SERVICE.removeItems(foodIds);
        Services.PANTRY_SERVICE.addItems(items);

        return items.size();
    }

    public void export(Path destination) throws PantsExportShoppingListException {
        Services.SHOPPING_SERVICE.export(destination);
    }
}
