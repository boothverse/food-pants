package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.ReportPeriod;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.decimal4j.arithmetic.CheckedScaleNfRoundingArithmetic;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddReportForm extends AddFoodInstanceForm {
    NutritionController nutritionController;

    protected JFormattedTextField startField, endField;
    protected DateFormat dateFormat;

    public AddReportForm(NutritionController controller, Component parent) {
        super("Create Nutritional Report", null, parent);
        nutritionController = controller;
    }

    public AddReportForm(String header, NutritionController controller, Component parent) {
        super(header, null, parent);
        nutritionController = controller;
    }

    @Override
    void initForm() {
        int i = 0;

        JPanel panel = new JPanel();
        panel.setPreferredSize(
            new Dimension(getWidth() / 2, panel.getHeight()));
        addLeftComponent(panel, ++i);

        dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        JLabel startLabel = new JLabel("Start Date");
        startField = new JFormattedTextField(dateFormat);

        JLabel endLabel = new JLabel("End Date");
        endField = new JFormattedTextField(dateFormat);

        addLeftComponent(startLabel, ++i);
        addRightComponent(startField, i);

        addLeftComponent(endLabel, ++i);
        addRightComponent(endField, i);

        addSubmitButton(e -> submitForm());
    }

    @Override
    protected void submitForm() {
        try {
            SimpleDateFormat finalFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            startField.setText(startField.getText() + " 00:00:00");
            endField.setText(endField.getText() + " 23:59:59");

            ReportPeriod reportPeriod = nutritionController.addReport(
                finalFormat.parse(startField.getText()),
                finalFormat.parse(endField.getText()));

            PageManager.getActivePage().notifyChange("add", null, reportPeriod);
            dispose();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Must specify a date in MM/dd/yyyy format.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}