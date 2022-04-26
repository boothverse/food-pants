package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.List;

public interface FoodInstanceController {
    public List<FoodInstance> getItems();
    public FoodInstance addItem(String foodId, Quantity<?> quantity);
    public FoodInstance editItem(String foodId, Quantity<?> quantity);
    public FoodInstance removeItem(String foodId);
}
