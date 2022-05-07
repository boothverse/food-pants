package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.business.services.util.UnitToString;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.NutritionType;
import org.boothverse.foodpants.persistence.ReportPeriod;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardGridBagPanel;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import org.boothverse.foodpants.ui.controllers.FoodController;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.boothverse.foodpants.ui.forms.EditNutritionForm;
import org.boothverse.foodpants.ui.forms.EditReportForm;
import org.boothverse.foodpants.ui.pages.Page;

import javax.measure.Quantity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ReportsItem extends StandardPanel implements ActionListener {

    StandardGridBagPanel contentPanel;

    protected JButton deleteButton;
    protected JButton editButton;

    Page parent;
    ReportPeriod reportPeriod;

    Map<NutritionType, Quantity> nutritionalInfo;

    public ReportsItem(ReportPeriod reportPeriod, Component parent) {
        contentPanel = new StandardGridBagPanel();

        this.parent = (Page) parent;
        this.reportPeriod = reportPeriod;

        initReport();
        add(contentPanel);
    }

    void initReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
        String start = dateFormat.format(reportPeriod.getStartDate());
        String end = dateFormat.format(reportPeriod.getEndDate());

        JLabel header = new JLabel(start + " - " + end);
        header.setFont(Style.headerStyle);

        NutritionController nutritionController = new NutritionController();
        List<NutritionInstance> items = nutritionController.getItems(
            reportPeriod.getStartDate(),
            reportPeriod.getEndDate()
        );

        if (items.size() == 0) {
            setVisible(false);
            return;
        }

        int i = 0;
        contentPanel.addMiddleComponent(header, ++i);

        FoodController foodController = new FoodController();

        nutritionalInfo = new HashMap<>();
        for (NutritionInstance item : items) {
            try {
                Food food = foodController.getFood(item.getFoodId());
                
                for (Map.Entry<NutritionType, Quantity<?>> entry : food.getNutrition().getNutritionInfo().entrySet()) {
                    if (nutritionalInfo.get(entry.getKey()) != null) {
                        nutritionalInfo.replace(entry.getKey(), nutritionalInfo.get(entry.getKey()).add(entry.getValue()));
                    } else {
                        nutritionalInfo.put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (PantsNotFoundException e) {}
        }

        for (Map.Entry<NutritionType, Quantity> infoEntry : nutritionalInfo.entrySet()) {
            contentPanel.addLeftComponent(new JLabel(infoEntry.getKey().name()), ++i);

            contentPanel.addRightComponent(new JLabel("(" +
                UnitToString.convertUnitToString(infoEntry.getValue().getUnit()) + ") "
                    + infoEntry.getValue().getValue()), i++);
        }

        // Add edit button
        editButton = new StandardButton("Edit");
        contentPanel.addLeftComponent(editButton, ++i);
        editButton.setVisible(false);
        editButton.addActionListener(this);

        // Add delete button
        deleteButton = new StandardButton("Delete");
        contentPanel.addRightComponent(deleteButton, i);
        deleteButton.setVisible(false);
        deleteButton.addActionListener(this);
    }

    public boolean hasNutritionalInfo() {
        return nutritionalInfo != null && nutritionalInfo.size() > 0;
    }

    public void setModification(boolean status) {
        if (editButton == null || deleteButton == null) {
            return;
        }

        editButton.setVisible(status);
        deleteButton.setVisible(status);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            EditReportForm editNutritionForm = new EditReportForm(reportPeriod, new NutritionController(), parent);
            editNutritionForm.setVisible(true);

            firePropertyChange("editItem", this, null);
        } else if (e.getSource() == deleteButton) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
                "Are you sure?", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.NO_OPTION) {
                return;
            }

            try {
                NutritionController nutritionController = new NutritionController();
                nutritionController.removeReport(reportPeriod.getId());
            } catch (PantsNotFoundException pantsNotFoundException) {}

            PageManager.getActivePage().notifyChange("remove", null, null);
            firePropertyChange("deleteItem", this, null);
        }
    }
}
