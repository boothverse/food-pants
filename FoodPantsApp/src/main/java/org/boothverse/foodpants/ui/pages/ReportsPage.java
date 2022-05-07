package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.ReportPeriod;
import org.boothverse.foodpants.ui.components.ReportsItem;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.boothverse.foodpants.ui.forms.AddReportForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ReportsPage extends NutritionPage {

    List<ReportsItem> reportsItems;
    JPanel panel;

    public ReportsPage() {
        panel = new JPanel(new FlowLayout());
        addReports();
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                StandardForm form = new AddReportForm(new NutritionController(), this);
                form.setVisible(true);
                break;
            default:
                super.actionPerformed(e);
                break;
        }
    }

    void addReports() {
        panel.removeAll();
        panel.setLayout(new GridLayout(0, 2));

        reportsItems = new ArrayList<>();

        NutritionController nutritionController = new NutritionController();
        List<ReportPeriod> reportPeriods = nutritionController.getReports();

        // Add report items to reports page
        for (ReportPeriod reportPeriod : reportPeriods) {
            ReportsItem item = new ReportsItem(reportPeriod, this);

            if (item.hasNutritionalInfo()) {
                reportsItems.add(item);
                panel.add(item);
            }
        }

        this.revalidate();
        this.repaint();
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        switch (message) {
            case "add":
            case "remove":
                addReports();
                break;
        }

        for (ReportsItem item : reportsItems) {
            item.setModification(NutritionPage.isModifyingPage());
        }
    }
}
