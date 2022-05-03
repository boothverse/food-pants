package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.persistence.NutritionType;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.GoalItem;
import org.boothverse.foodpants.ui.components.PantryItem;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.boothverse.foodpants.ui.forms.AddGoalForm;
import org.boothverse.foodpants.ui.forms.AddNutritionForm;
import org.boothverse.foodpants.ui.forms.EditFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.StandardForm;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class GoalsPage extends NutritionPage {

    JPanel contentPanel;

    public GoalsPage() {
        JPanel listWrapper = new JPanel(new FlowLayout());
        contentPanel = new JPanel();

        addAllGoals();
        listWrapper.add(contentPanel);
        add(listWrapper);

        listWrapper.setBackground(Style.TRANSPARENT);
        contentPanel.setBackground(Style.TRANSPARENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                StandardForm form = new AddGoalForm(new NutritionController(), this);
                form.setVisible(true);
                break;
            default:
                super.actionPerformed(e);
                break;
        }
    }

    protected void addAllGoals() {
        contentPanel.removeAll();
        contentPanel.setLayout(new GridLayout(0, 2));
        NutritionController nutritionController = new NutritionController();

        List<Goal> goals = nutritionController.getGoals();
        for (Goal goal : goals) {
            contentPanel.add(new GoalItem(goal));
        }
    }


    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        if (Objects.equals(message, "add")) {
            addAllGoals();
            revalidate();
            repaint();
        }
//        else if (Objects.equals(message, "remove")) {
//        }
//        else if (Objects.equals(message, "edit")) {
//        }
//        else if (Objects.equals(message, "update")) {
//        }
    }
}
