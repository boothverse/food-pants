package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.ReportPeriod;
import org.boothverse.foodpants.ui.components.ReportsItem;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.boothverse.foodpants.ui.forms.AddReportForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class ReportsPage extends NutritionPage {

    List<ReportPeriod> reportPeriods;
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
        panel.setLayout(new FlowLayout());

        NutritionController nutritionController = new NutritionController();
        reportPeriods = nutritionController.getReports();

        // Add report items to reports page
        for (ReportPeriod reportPeriod : reportPeriods) {
            panel.add(new ReportsItem(reportPeriod.getStartDate(), reportPeriod.getEndDate()));
        }

        this.revalidate();
        this.repaint();
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        if (Objects.equals(message, "add")) {
            addReports();
        }
//        else if (Objects.equals(message, "remove")) {
//        }
//        else if (Objects.equals(message, "edit")) {
//        }
//        else if (Objects.equals(message, "update")) {
//        }
    }
}
