package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Quantity;
import java.util.ArrayList;
import java.util.List;

public class PantryService extends FoodInstanceService {

    private static final String DB_NAME = "PANTRY";

    public PantryService() {
        super(DB_NAME);
    }

    /**
     *
     * @param itemsToCheck
     * @return
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
     *
     * @param itemsToAdd
     */
    public void addItems(List<FoodInstance> itemsToAdd) {
        itemsToAdd.forEach(itemToAdd -> {
            String id = itemToAdd.getId();
            if (items.containsKey(id)) {    // food already exists in pantry, just add more
                Quantity curQuantity = items.get(id).getQuantity();
                itemToAdd.setQuantity(itemToAdd.getQuantity().add(curQuantity));
            }
            items.put(id, itemToAdd);
            dao.save(itemToAdd);
        });
    }

    /**
     *
     * @param foodId
     * @param quantity
     */
    public void removeItem(String foodId, Quantity quantity) {
        // TODO: test with quantities of diff types and units
        if (items.containsKey(foodId)) {
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
    }

    /**
     *
     * @param item
     * @return
     */
    public Boolean contains(FoodInstance item) {
        return items.containsKey(item.getId());
    }

    /**
     *
     * @param query
     * @return
     */
    public List<FoodInstance> searchByFoodName(String query) {
        FoodService foodService = Services.FOOD_SERVICE;
        return items.values().stream()
            .filter(item -> foodService.getFood(item.getId()).getName().startsWith(query))
            .toList();
    }
}
