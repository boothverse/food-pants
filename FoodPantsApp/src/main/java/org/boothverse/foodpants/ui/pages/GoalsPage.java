package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.GoalItem;
import org.boothverse.foodpants.ui.components.ReportsItem;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.boothverse.foodpants.ui.forms.AddGoalForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoalsPage extends NutritionPage {

    JPanel contentPanel;
    List<GoalItem> items;

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

        items = new ArrayList<>();

        List<Goal> goals = nutritionController.getGoals();
        for (Goal goal : goals) {
            GoalItem item = new GoalItem(goal);

            items.add(item);
            contentPanel.add(item);
        }
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        switch (message) {
            case "add":
            case "remove":
            case "edit":
                addAllGoals();
                revalidate();
                repaint();
                break;
        }

        for (GoalItem item : items) {
            item.setModification(NutritionPage.isModifyingPage());
        }
    }
}
