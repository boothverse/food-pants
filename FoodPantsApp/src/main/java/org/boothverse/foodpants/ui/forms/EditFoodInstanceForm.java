package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.controllers.FoodController;
import org.boothverse.foodpants.ui.controllers.FoodInstanceController;

import java.awt.*;

import static org.boothverse.foodpants.ui.components.QuantitySelector.unitClasses;

public class EditFoodInstanceForm extends AddFoodInstanceForm {
    FoodController foodController = new FoodController();

    public EditFoodInstanceForm(FoodInstance editItem, FoodInstanceController controller, Component parent) {
        super("Edit Food",controller, parent);

        try {
            foodSearchBar.setSelectedItem(foodController.getFood(editItem.getId()));
        }
        catch (PantsNotFoundException e) {
            e.printStackTrace();
        }

        foodSearchBar.setSelectedItem("Apple");
        quantityPanel.getQuantityValueField().setText(editItem.getQuantity().getValue().toString() + "");

        if (editItem.getQuantity() != null) {
            for (int i = 0; i < unitClasses.length; i++) {
                if (editItem.getQuantity().getUnit().equals(unitClasses[i])) {
                    quantityPanel.getQuantityUnitBox().setSelectedIndex(i);
                    break;
                }
            }
        }
    }
}
