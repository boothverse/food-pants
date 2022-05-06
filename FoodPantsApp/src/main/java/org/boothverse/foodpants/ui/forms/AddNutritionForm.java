package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.controllers.NutritionController;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class AddNutritionForm extends AddFoodInstanceForm {

    protected NutritionController nutritionController;
    protected JFormattedTextField dateField;
    protected SimpleDateFormat dateFormat;

    public AddNutritionForm(String header, NutritionController controller, Component parent) {
        super(header, null, parent);
        nutritionController = controller;
    }

    @Override
    void initForm() {
        int i = 0;
        addLeftComponent(new JLabel("Food"), ++i);
        addRightComponent(foodSearchBar, i);

        addRightComponent(editFoodButton, ++i);
        addRightComponent(createFoodButton, ++i);
        addRightComponent(new JPanel(),++i);

        addLeftComponent(new JLabel("Quantity"), ++i);
        addRightComponent(quantityPanel, i);
        addRightComponent(new JPanel(),++i);

        addLeftComponent(new JLabel("Consumed At"), ++i);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        dateField = new JFormattedTextField(dateFormat);
        addRightComponent(dateField, i);

        initSubmitButton();
    }

    void initSubmitButton() {
        addSubmitButton(e -> {
            if (foodSearchBar.getSelectedItem() != null && !quantityPanel.isEmpty()) {
                try {
                    FoodInstance newFood = nutritionController.addItem(
                        ((Food) Objects.requireNonNull(foodSearchBar.getSelectedItem())).getId(),
                        quantityPanel.getSelectedQuantity(),
                        dateFormat.parse(dateField.getText()));

                    PageManager.getActivePage().notifyChange("add", null, newFood);
                    dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Must specify a date in MM/dd/yyyy hh:mm format.",
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
