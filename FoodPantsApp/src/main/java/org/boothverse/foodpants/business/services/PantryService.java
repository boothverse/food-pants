package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.List;
import java.util.Map;

public class PantryService extends FoodInstanceService {

    private static final String DB_FILENAME = "Pantry.json";

    public PantryService() {
        super(DB_FILENAME);
    }

    /**
     *
     * @param items
     * @return
     */
    public List<FoodInstance> getMissing(List<FoodInstance> items) {
        return null;
    }

    /**
     *
     * @param items
     */
    public void addItems(List<FoodInstance> items) {

    }

    /**
     *
     * @param foodId
     * @param quantity
     */
    public void removeItem(String foodId, Quantity<?> quantity) {

    }

    /**
     *
     * @param item
     * @return
     */
    public Boolean contains(FoodInstance item) {
        return null;
    }

    /**
     *
     * @param query
     * @return
     */
    public Map<String, FoodInstance> searchByFoodName(String query) {
        return null;
    }
}
