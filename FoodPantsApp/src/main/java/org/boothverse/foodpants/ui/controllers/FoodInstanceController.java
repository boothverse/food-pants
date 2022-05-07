package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.exceptions.PantsConversionFailedException;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.List;

public interface FoodInstanceController {
    public List<FoodInstance> getItems();
    public FoodInstance addItem(String foodId, Quantity<?> quantity) throws PantsConversionFailedException;
    public FoodInstance editItem(String foodId, Quantity<?> quantity) throws PantsNotFoundException;
    public void removeItem(String foodId) throws PantsNotFoundException;
}
