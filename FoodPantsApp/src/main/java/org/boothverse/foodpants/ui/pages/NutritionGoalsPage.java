package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.GoalItem;
import org.boothverse.foodpants.ui.forms.AddGoalForm;
import org.boothverse.foodpants.ui.forms.AddNutritionForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NutritionGoalsPage extends NutritionPage {

    public NutritionGoalsPage() {
        JPanel listWrapper = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new GoalItem("Protein (grams)", 140, true));
        panel.add(new GoalItem("Carbs (grams)", 200, true));
        panel.add(new GoalItem("Fat (grams)", 70, true));
        listWrapper.add(panel);
        add(listWrapper);

        listWrapper.setBackground(Style.TRANSPARENT);
        panel.setBackground(Style.TRANSPARENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                StandardForm form = new AddGoalForm("Add Nutrition Goal");
                form.setLocationRelativeTo(this);
                form.setVisible(true);
                break;
            default:
                super.actionPerformed(e);
                break;
        }
    }
}
