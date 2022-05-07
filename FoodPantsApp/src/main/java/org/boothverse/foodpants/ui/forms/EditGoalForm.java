package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.persistence.NutritionType;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.controllers.NutritionController;

import javax.swing.*;
import java.awt.*;

import static org.boothverse.foodpants.ui.components.QuantitySelector.unitClasses;

public class EditGoalForm extends AddGoalForm {

    protected Goal goal;

    public EditGoalForm(Goal goal, NutritionController controller, Component parent) {
        super("Edit Nutrition Goal", controller, parent);

        goalTypeDropdown.setSelectedIndex((goal.getGoalType() == GoalType.MAXIMIZE) ? 1 : 0);
        quantityPanel.getQuantityValueField().setText(goal.getDailyQuantity().getValue().toString() + "");

        if (goal.getDailyQuantity() != null) {
            for (int i = 0; i < unitClasses.length; i++) {
                if (goal.getDailyQuantity().getUnit().equals(unitClasses[i])) {
                    quantityPanel.getQuantityUnitBox().setSelectedIndex(i);
                    break;
                }
            }
        }

        this.goal = goal;
    }

    @Override
    protected void submitForm() {
        if (!quantityPanel.isEmpty()) {
            GoalType goalType = (goalTypeDropdown.getSelectedItem().toString().equals("Minimum")) ?
                GoalType.MINIMIZE : GoalType.MAXIMIZE;

            NutritionType nutritionType = NutritionType.valueOf(nutritionTypeDropdown.getSelectedItem().toString());

            try {
                // Edit goal
                Goal newGoal = nutritionController.editGoal(
                    goal.getId(),
                    goalType,
                    quantityPanel.getSelectedQuantity(), nutritionType
                );

                PageManager.getActivePage().notifyChange("edit", null, newGoal);
            } catch (PantsNotFoundException ex) {}

            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Must specify quantity of goal.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
