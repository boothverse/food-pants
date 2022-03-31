package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.forms.AddNutritionForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import java.awt.event.ActionEvent;

public class NutritionPage extends Page {
    protected static final String[] labels = {"+", "Timeline", "Goals", "Report"};


    public NutritionPage() {
        super(labels);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+" -> {
                StandardForm form = new AddNutritionForm("Add Nutrition Item");
                form.setLocationRelativeTo(this);
                form.setVisible(true);
            }
            case "Timeline" -> {
                System.out.println("Timeline clicked");
                PageManager.setPage("Nutrition");
            }
            case "Goals" -> PageManager.setPage("Goals");
            case "Report" -> PageManager.setPage("Report");
        }
    }
}
