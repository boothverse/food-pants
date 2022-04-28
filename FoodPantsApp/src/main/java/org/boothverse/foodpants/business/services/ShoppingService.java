package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.FoodInstance;

import java.nio.file.Path;
import java.util.List;

public class ShoppingService extends FoodInstanceService {

    private static final String DB_FILENAME = "ShoppingList.json";

    public ShoppingService() {
        super(DB_FILENAME);
    }

    public void removeItems(List<String> foodIds) {

    }

    public void addItems(List<FoodInstance> items) {

    }

    public boolean export(String fileFormat, Path destination) {
        return false;
    }
}
