package org.boothverse.foodpants.ui.forms;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class AddFoodInstanceForm extends StandardForm {
    protected final int TXT_FIELD_WIDTH = 25;
    String[] quantityOptions = {"unit", "g", "oz", "fl oz", "cups"};

    JTextField nameField;
    JPanel quantityPanel;

    public AddFoodInstanceForm() {
        super("Add Food");

        initSwing();
        initForm();
    }

    private void initSwing() {
        // TODO: this will need to function as a search bar
        nameField = new JTextField(TXT_FIELD_WIDTH);

        quantityPanel = new JPanel(new BorderLayout());
        JComboBox<String> quantityChoice = new JComboBox<>(quantityOptions);
        JFormattedTextField quantityValue = new JFormattedTextField(NumberFormat.getNumberInstance());
        quantityPanel.add(quantityChoice, BorderLayout.WEST);
        quantityPanel.add(quantityValue, BorderLayout.CENTER);
    }

    @Override
    void initForm() {
        addLeftComponent(new JLabel("Name"), 1);
        addRightComponent(nameField, 1);

        addLeftComponent(new JLabel("Quantity"), 2);
        addRightComponent(quantityPanel, 2);

        addSubmitButton(e -> dispose());
    }
}
