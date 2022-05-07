package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.exceptions.PantsConversionFailedException;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.business.services.util.UnitToString;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.controllers.PantryController;
import tech.units.indriya.quantity.Quantities;

import javax.swing.*;
import java.awt.*;

public class ConsumeForm extends StandardForm {
    FoodInstance food;
    int numRows;
    JSpinner numEnter;
    Double max;

    PantryController pantryController = new PantryController();

    public ConsumeForm(String header, Component parent, FoodInstance consuming) {
        super(header, parent);
        food = consuming;
        max = food.getQuantity().getValue().doubleValue();
        numRows = 0;

        initForm();
    }

    @Override
    void initForm() {

        JPanel outOfPanel = new JPanel();
        outOfPanel.setLayout(new GridLayout(1,0));
        outOfPanel.setBackground(getBackground());
        outOfPanel.setPreferredSize(new Dimension(getWidth() / 2, 20));

        String text = Double.toString(Math.abs(max));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        double stepSize = Math.pow(0.1, decimalPlaces);
        SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, max.doubleValue(), stepSize);
        numEnter = new JSpinner(model);

        outOfPanel.add(numEnter);
        JLabel maxPanel = new JLabel("      / " + max);
        outOfPanel.add(maxPanel);

        addLeftComponent(new JLabel("Units: "), numRows);
        JTextField unit = new JTextField(UnitToString.convertUnitToString(food.getQuantity().getUnit()));
        unit.setEditable(false);
        addRightComponent(unit, numRows);

        addLeftComponent(new JLabel("Consume Amount: "), ++numRows);
        addRightComponent(outOfPanel, numRows);

        addSubmitButton(e -> {
            try {
                pantryController.consume(food.getId(), Quantities.getQuantity((Double) numEnter.getValue(),food.getQuantity().getUnit()));
            } catch (PantsNotFoundException | PantsConversionFailedException ex) {
                ex.printStackTrace();
            }
            PageManager.getActivePage().notifyChange("update", null, null);
            dispose();
        });
    }
}
