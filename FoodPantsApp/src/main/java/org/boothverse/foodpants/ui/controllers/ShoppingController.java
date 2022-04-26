package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ShoppingController implements FoodInstanceController {
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

    public Integer purchaseItems(List<String> foodIds) {
        return null;
    }

    public boolean export(String fileFormat, Path destination) {
        return false;
    }
}
