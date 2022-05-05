package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.ui.controllers.NutritionController;

import java.awt.*;

import static org.boothverse.foodpants.ui.components.QuantitySelector.unitClasses;

public class EditNutritionForm extends AddNutritionForm {

    NutritionInstance editItem;

    public EditNutritionForm(NutritionInstance nutritionInstance, Component parent) {
        super("Edit Nutritional Item", new NutritionController(), parent);

        editItem = nutritionInstance;
        quantityPanel.getQuantityValueField().setText(editItem.getQuantity().getValue().toString() + "");

        String dateText = dateFormat.format(nutritionInstance.getConsumedAt());
        dateField.setText(dateText);

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
