package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.controllers.FoodInstanceController;

import java.awt.*;

import static org.boothverse.foodpants.ui.components.QuantitySelector.unitClasses;

public class EditFoodInstanceForm extends AddFoodInstanceForm {
    public EditFoodInstanceForm(FoodInstance editItem, FoodInstanceController controller, Component parent) {
        super("Edit Food",controller, parent);

        // TODO: fix this when foodServices is implemented
        //foodSearchBar.setSelectedItem(Services.FOOD_SERVICE.getFoodName(editItem.getId()));

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

        System.out.println(editItem.getQuantity().getUnit().toString());
    }
}
