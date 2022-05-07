package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.ReportPeriod;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.controllers.NutritionController;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditReportForm extends AddReportForm {

    protected ReportPeriod reportPeriod;

    public EditReportForm(ReportPeriod period, NutritionController controller, Component parent) {
        super("Edit Nutrition Report", controller, parent);

        reportPeriod = period;

        startField.setText(dateFormat.format(period.getStartDate()));
        endField.setText(dateFormat.format(period.getEndDate()));
    }

    @Override
    protected void submitForm() {
        try {
            SimpleDateFormat finalFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            startField.setText(startField.getText() + " 00:00:00");
            endField.setText(endField.getText() + " 23:59:59");

            Date startDate = finalFormat.parse(startField.getText());
            Date endDate = finalFormat.parse(endField.getText());

            nutritionController.editReport(reportPeriod.getId(), startDate, endDate);
            PageManager.getActivePage().notifyChange("add", null, null);
            dispose();
        } catch (ParseException | PantsNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Must specify a date in MM/dd/yyyy format.",
                "Error", JOptionPane.ERROR_MESSAGE);

            System.out.println(ex.getMessage());
        }
    }
}
