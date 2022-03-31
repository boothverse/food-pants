package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.forms.AddGoalForm;
import org.boothverse.foodpants.ui.forms.AddNutritionForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import java.awt.event.ActionEvent;

public class NutritionGoalsPage extends NutritionPage {

    public NutritionGoalsPage() {

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
