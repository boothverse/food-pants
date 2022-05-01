package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.services.exceptions.PantsExportShoppingListException;
import org.boothverse.foodpants.business.services.util.ShoppingListExporter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShoppingService extends FoodInstanceService {

    private static final String DB_NAME = "shoppingList";

    public ShoppingService() {
        super(DB_NAME);
    }

    public void removeItems(List<String> foodIds) {
        foodIds.forEach(id -> {
            items.remove(id);
            dao.remove(id);
        });
    }

    public void export(Path destination) throws PantsExportShoppingListException {
        FoodService foodService = Services.FOOD_SERVICE;

        new ShoppingListExporter().export(destination, items.values()
            .stream()
            .map(item -> new ShoppingListExporter.ShoppingListItem(foodService.getFoodName(item.getId()), item.getQuantity().toString()))
            .toList()
        );
    }
}
