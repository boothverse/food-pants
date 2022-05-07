package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.ArrayList;
import java.util.List;

/**
 * service dealing with processing food instances within the pantry
 */
public class PantryService extends FoodInstanceService {

    private static final String DB_NAME = "PANTRY";

    public PantryService() {
        super(DB_NAME);
    }

    /**
     * Returns a list of ingredients missing from the pantry which are in the given list
     *
     * @param itemsToCheck a list of items from a recipe which may or may not be in the pantry
     * @return a new list of items on the list which are not in the pantry
     */
    public List<FoodInstance> getMissing(List<FoodInstance> itemsToCheck) {
        List<FoodInstance> missing = new ArrayList<>();

        itemsToCheck.forEach(itemToCheck -> {
            String id = itemToCheck.getId();
            if (items.containsKey(id)) {                        // food exists in pantry
                FoodInstance pantryItem = items.get(id);
                // TODO: make sure that getQuantity().subtract()
                //  does not modify the quantity stored in the pantry!!
                Quantity quantity = pantryItem.getQuantity().subtract((Quantity) itemToCheck.getQuantity());
                if (quantity.getValue().doubleValue() < 0) {    // but we don't have enough of it
                    missing.add(new FoodInstance(id, quantity.multiply(-1)));
                }
            } else {                                            // food does not exist in pantry
                missing.add(itemToCheck);
            }
        });

        return missing;
    }

    /**
     * Removes the given item from the pantry
     *
     * @param foodId the id of the food being removed
     * @param quantity the quantity of that food to remove
     */
    public void removeItem(String foodId, Quantity quantity) throws PantsNotFoundException {
        // TODO: test with quantities of diff types and units
        if (!items.containsKey(foodId)) throw new PantsNotFoundException("food " + foodId + " not found");

        FoodInstance item = items.get(foodId);
        quantity = item.getQuantity().subtract(quantity);

        if (quantity.getValue().doubleValue() > 0) {    // remove part
            item.setQuantity(quantity);
            items.replace(foodId, item);
            dao.save(item);
        } else {                                        // remove all
            items.remove(foodId);
            dao.remove(foodId);
        }
    }

    /**
     * Determines whether the given item is in the pantry
     *
     * @param item the food instance to be tested
     * @return whether the item was in the pantry of not
     */
    public Boolean contains(FoodInstance item) {
        return items.containsKey(item.getId());
    }

    /**
     * Produces a list of foods based on the given string
     *
     * @param query a string being used as a search key
     * @return a list of foods related to the search key
     */
    public List<FoodInstance> searchByFoodName(String query) {
        FoodService foodService = Services.FOOD_SERVICE;
        return items.values().stream()
            .filter(item -> {
                try {
                    return foodService.getFood(item.getId()).getName().startsWith(query);
                } catch (PantsNotFoundException e) {
                    return false;
                }
            })
            .toList();
    }
}
