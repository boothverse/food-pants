package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.*;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AddGoalForm extends AddFoodInstanceForm {

    protected NutritionController nutritionController;

    public AddGoalForm(NutritionController controller, Component parent) {
        super("Add Goal", null, parent);
        nutritionController = controller;
    }

    @Override
    void initForm() {
        new Goal("1", GoalType.MINIMIZE,
            Quantities.getQuantity(15, Units.GRAM), NutritionType.PROTEIN);

        int i = 0;
        addLeftComponent(new JLabel("Goal Type"), ++i);
        JComboBox goalTypeDropdown = new JComboBox(new String[]{"Minimum", "Maximum"});
        addRightComponent(goalTypeDropdown, i);

        addLeftComponent(new JLabel("Nutritional Type"), ++i);
        String[] nutritionTypes = Arrays.stream(NutritionType.values())
                                        .map(NutritionType::name)
                                        .toArray(String[]::new);

        JComboBox nutritionTypeDropdown = new JComboBox(nutritionTypes);
        addRightComponent(nutritionTypeDropdown, i);


        addLeftComponent(new JLabel("Quantity"), ++i);
        addRightComponent(quantityPanel, i);

        addSubmitButton(e -> {
            if (!quantityPanel.isEmpty()) {
                GoalType goalType = (goalTypeDropdown.getSelectedItem().toString().equals("Minimum")) ?
                    GoalType.MINIMIZE : GoalType.MAXIMIZE;

                NutritionType nutritionType = NutritionType.valueOf(nutritionTypeDropdown.getSelectedItem().toString());

                // Add goal
                Goal newGoal = nutritionController.addGoal(
                    goalType, quantityPanel.getSelectedQuantity(), nutritionType
                );

                PageManager.getActivePage().notifyChange("add", null, newGoal);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Must specify quantity of goal!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}