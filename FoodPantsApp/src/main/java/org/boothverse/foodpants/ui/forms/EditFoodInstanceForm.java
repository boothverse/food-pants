package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.FoodInstance;

import javax.measure.Unit;
import javax.measure.quantity.Dimensionless;

public class EditFoodInstanceForm extends AddFoodInstanceForm {
    public EditFoodInstanceForm(FoodInstance editItem) {
        super();

        // TODO: fix this when foodServices is implemented
        //foodSearchBar.setSelectedItem(Services.FOOD_SERVICE.getFoodName(editItem.getId()));

        foodSearchBar.setSelectedItem("Apple");
        quantityValueField.setText(editItem.getQuantity().getValue().toString() + "");

        if (editItem.getQuantity() != null) {
            for (int i = 0; i < unitClasses.length; i++) {
                if (editItem.getQuantity().getUnit().equals(unitClasses[i])) {
                    quantityUnitBox.setSelectedIndex(i);
                    break;
                }
            }
        }

        System.out.println(editItem.getQuantity().getUnit().toString());
    }
}
