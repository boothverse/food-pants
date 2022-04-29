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
        new ShoppingListExporter().export(destination, new ArrayList<>(items.values()));
    }
}
