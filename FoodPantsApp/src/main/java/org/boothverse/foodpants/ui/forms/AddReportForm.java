package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.ReportPeriod;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.controllers.NutritionController;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddReportForm extends AddFoodInstanceForm {
    NutritionController nutritionController;

    public AddReportForm(NutritionController controller, Component parent) {
        super("Create Nutritional Report", null, parent);
        nutritionController = controller;
    }

    @Override
    void initForm() {
        int i = 0;

        JPanel panel = new JPanel();
        panel.setPreferredSize(
            new Dimension(getWidth() / 2, panel.getHeight()));
        addLeftComponent(panel, ++i);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JLabel startLabel = new JLabel("Start Date");
        JFormattedTextField startField = new JFormattedTextField(dateFormat);

        JLabel endLabel = new JLabel("End Date");
        JFormattedTextField endField = new JFormattedTextField(dateFormat);

        addLeftComponent(startLabel, ++i);
        addRightComponent(startField, i);

        addLeftComponent(endLabel, ++i);
        addRightComponent(endField, i);

        addSubmitButton(e -> {
            try {
                ReportPeriod reportPeriod = nutritionController.addReport(
                    dateFormat.parse(startField.getText()),
                    dateFormat.parse(endField.getText()));

                PageManager.getActivePage().notifyChange("add", null, reportPeriod);
                dispose();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Must specify a date in MM/dd/yyyy format.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}