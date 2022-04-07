package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.List;

public class PantryService extends FoodInstanceService {

    private static final String DB_FILENAME = "Pantry.json";

    public PantryService() {
        super(DB_FILENAME);
    }

    public List<FoodInstance> getMissing(List<FoodInstance> items) {
        return null;
    }

    public void addItems(List<FoodInstance> items) {

    }

    public void removeItem(String foodId, Quantity<?> quantity) {

    }

    public Boolean contains(FoodInstance item) {
        return null;
    }

}
