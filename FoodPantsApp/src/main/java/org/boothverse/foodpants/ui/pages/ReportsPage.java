package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.forms.AddNutritionForm;
import org.boothverse.foodpants.ui.forms.AddReportForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import java.awt.event.ActionEvent;

public class ReportsPage extends NutritionPage {
    public ReportsPage() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                StandardForm form = new AddReportForm("Add Nutrition Report", this);
                form.setVisible(true);
                break;
            default:
                super.actionPerformed(e);
                break;
        }
    }
}
