package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.persistence.NutritionType;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.GoalItem;
import org.boothverse.foodpants.ui.forms.AddGoalForm;
import org.boothverse.foodpants.ui.forms.AddNutritionForm;
import org.boothverse.foodpants.ui.forms.StandardForm;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GoalsPage extends NutritionPage {

    public GoalsPage() {
        JPanel listWrapper = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new GridLayout(0, 2));

        // TODO: remove static data
        panel.add(new GoalItem(new Goal("1", GoalType.MINIMIZE,
            Quantities.getQuantity(15, Units.GRAM), NutritionType.PROTEIN)));

        panel.add(new GoalItem(new Goal("2", GoalType.MAXIMIZE,
            Quantities.getQuantity(10, Units.GRAM), NutritionType.TOTAL_SUGAR)));

        panel.add(new GoalItem(new Goal("3", GoalType.MAXIMIZE,
            Quantities.getQuantity(30, Units.GRAM), NutritionType.TOTAL_FAT)));

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
