package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.forms.AddNutritionForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NutritionPage extends Page {
    protected static final String[] labels = {"+", "Timeline", "Goals", "Report"};


    public NutritionPage() {
        super(labels);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                StandardForm form =  new AddNutritionForm("Add Nutrition Item");
                form.setLocationRelativeTo(this);
                form.setVisible(true);
                break;
            case "Timeline":
                PageManager.setPage("Nutrition");
            case "Goals":
                PageManager.setPage("Goals");
                break;
            case "Report":
                PageManager.setPage("Report");
                break;
        }
    }
}
