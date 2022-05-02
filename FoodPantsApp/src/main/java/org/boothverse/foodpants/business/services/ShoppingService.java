package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.services.exceptions.PantsExportShoppingListException;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.business.services.util.ShoppingListExporter;

import java.nio.file.Path;
import java.util.List;

public class ShoppingService extends FoodInstanceService {

    private static final String DB_NAME = "shoppingList";

    public ShoppingService() {
        super(DB_NAME);
    }

    /**
     * Removes the specified item from the shopping service.
     *
     * @param foodIds
     * @throws PantsNotFoundException
     */
    public void removeItems(List<String> foodIds) throws PantsNotFoundException {
        for (String id : foodIds) {
            removeItem(id);
        }
    }

    public void removeAllItems() {
        items.keySet().forEach(id -> {
            try {
                removeItem(id);
            } catch (PantsNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Translates the shopping list into a pdf.
     *
     * @param destination
     * @throws PantsExportShoppingListException
     */
    public void export(Path destination) throws PantsExportShoppingListException {
        FoodService foodService = Services.FOOD_SERVICE;

        new ShoppingListExporter().export(destination, items.values()
            .stream()
            .map(item -> {
                try {
                    return new ShoppingListExporter.ShoppingListItem(foodService.getFoodName(item.getId()), item.getQuantity().toString());
                } catch (PantsNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            })
            .toList()
        );
    }
}
