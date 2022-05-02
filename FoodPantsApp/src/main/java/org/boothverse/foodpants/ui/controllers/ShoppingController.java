package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsExportShoppingListException;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.nio.file.Path;
import java.util.List;

public class ShoppingController implements FoodInstanceController {
    /**
     * Returns a list of shopping items.
     *
     * @return
     */
    @Override
    public List<FoodInstance> getItems() {
        return Services.SHOPPING_SERVICE.getItems();
    }

    /**
     * Adds an item to the shopping list.
     *
     * @param foodId
     * @param quantity
     * @return
     */
    @Override
    public FoodInstance addItem(String foodId, Quantity<?> quantity) {
        return Services.SHOPPING_SERVICE.addItem(foodId, quantity);
    }

    /**
     * Modifies an item with the given information.
     *
     * @param foodId
     * @param quantity
     * @return
     * @throws PantsNotFoundException
     */
    @Override
    public FoodInstance editItem(String foodId, Quantity<?> quantity) throws PantsNotFoundException {
        return Services.SHOPPING_SERVICE.editItem(foodId, quantity);
    }

    /**
     * Removes the specified item from the list.
     *
     * @param foodId
     * @throws PantsNotFoundException
     */
    @Override
    public void removeItem(String foodId) throws PantsNotFoundException {
        Services.SHOPPING_SERVICE.removeItem(foodId);
    }

    public void removeAllItems() {
        Services.SHOPPING_SERVICE.removeAllItems();
    }

    /**
     * Removes the list of items from the shopping list and adds them to the pantry.
     *
     * @param foodIds
     * @return
     * @throws PantsNotFoundException
     */
    public Integer purchaseItems(List<String> foodIds) throws PantsNotFoundException {
        List<FoodInstance> items = Services.SHOPPING_SERVICE.getItems();
        Services.SHOPPING_SERVICE.removeItems(foodIds);
        Services.PANTRY_SERVICE.addItems(items);

        return items.size();
    }

    /**
     * Exports the shopping list as a pdf.
     *
     * @param destination
     * @throws PantsExportShoppingListException
     */
    public void export(Path destination) throws PantsExportShoppingListException {
        Services.SHOPPING_SERVICE.export(destination);
    }
}
