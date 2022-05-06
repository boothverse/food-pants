package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.controllers.FoodController;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.boothverse.foodpants.ui.pages.NutritionPage;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Objects;

import static org.boothverse.foodpants.ui.components.QuantitySelector.unitClasses;

public class EditNutritionForm extends AddNutritionForm {

    NutritionInstance editItem;

    public EditNutritionForm(NutritionInstance nutritionInstance, Component parent) {
        super("Edit Nutritional Item", new NutritionController(), parent);

        editItem = nutritionInstance;
        quantityPanel.getQuantityValueField().setText(editItem.getQuantity().getValue().toString() + "");

        try {
            foodSearchBar.setSelectedItem(new FoodController().getFood(editItem.getFoodId()));
        } catch (PantsNotFoundException e) {}

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

    @Override
    void initSubmitButton() {
        addSubmitButton(e -> {
            if (foodSearchBar.getSelectedItem() != null && !quantityPanel.isEmpty()) {
                try {
                    FoodInstance newFood = nutritionController.editItem(
                        editItem.getId(),
                        ((Food) Objects.requireNonNull(foodSearchBar.getSelectedItem())).getId(),
                        quantityPanel.getSelectedQuantity(),
                        dateFormat.parse(dateField.getText()));

                    PageManager.getActivePage().notifyChange("edit", null, newFood);
                    dispose();
                } catch (ParseException | PantsNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid form submitted. Please revise and try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Must select a food and quantity to be added",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
