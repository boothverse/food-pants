package org.boothverse.foodpants.business.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.services.exceptions.PantsExportShoppingListException;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.business.services.util.ShoppingListExporter;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

/**
 * service dealing with processing food instances within the shopping list
 */
public class ShoppingService extends FoodInstanceService {
    private static Logger logger = LogManager.getLogger(ShoppingService.class);
    private static final String DB_NAME = "shoppingList";

    /**
     * Creates a new shopping service object
     */
    public ShoppingService() {
        super(DB_NAME);
        logger.info("Loaded shopping list from database");
    }

    /**
     * Removes the specified item from the shopping service.
     *
     * @param foodIds the specified item
     * @throws PantsNotFoundException
     */
    public void removeItems(List<String> foodIds) throws PantsNotFoundException {
        for (String id : foodIds) {
            logger.info("Removing item with id " + id + " from shopping list");
            removeItem(id);
        }
    }

    /**
     * Removes all items from the service and database
     */
    public void removeAllItems() {
        List<String> ids = items.keySet().stream().toList();
        logger.info("Removing all items from shopping list");
        try {
            removeItems(ids);
        } catch (PantsNotFoundException e) {
            logger.warn("Item in list was not found in shopping list");
        }
    }

    /**
     * Translates the shopping list into a pdf.
     *
     * @param destination the destination of the created pdf
     * @throws PantsExportShoppingListException
     */
    public void export(Path destination) throws PantsExportShoppingListException {
        FoodService foodService = Services.FOOD_SERVICE;
        logger.info("Exporting shopping list to " + destination);
        new ShoppingListExporter().export(destination, items.values()
            .stream()
            .map(item -> {
                try {
                    logger.info("Adding item with id " + item.getId() + " to exported shopping list");
                    return new ShoppingListExporter.ShoppingListItem(foodService.getFoodName(item.getId()), item.getQuantity().toString());
                } catch (PantsNotFoundException e) {
                    logger.warn("Item with id " + item.getId() + " could not be added to exported shopping list");
                    return null;
                }
            })
            .toList()
        );
    }
}
