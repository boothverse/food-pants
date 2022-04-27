package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.components.FoodSearchBar;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class AddFoodInstanceForm extends StandardForm {
    protected final int TXT_FIELD_WIDTH = 25;
    String[] quantityOptions = {"unit", "g", "oz", "fl oz", "cups"};

    FoodSearchBar nameField;
    JPanel quantityPanel;

    public AddFoodInstanceForm() {
        super("Add Food");

        initSwing();
        initForm();
    }

    private void initSwing() {
        // TODO: this will need to function as a search bar
        nameField = new FoodSearchBar();
        nameField.populate(java.util.List.of(new String[]{"Apple", "Orange", "Banana"}));

        quantityPanel = new JPanel(new BorderLayout());
        JComboBox<String> quantityChoice = new JComboBox<>(quantityOptions);
        JFormattedTextField quantityValue = new JFormattedTextField(NumberFormat.getNumberInstance());

        // Use set preferred size if you want GridBagLayout to actually listen to what you want and not
        // ignore you like the garbage swing layout manager that it is.
        quantityValue.setPreferredSize(new Dimension(getWidth() / 2, quantityValue.getHeight()));
        quantityPanel.add(quantityChoice, BorderLayout.WEST);
        quantityPanel.add(quantityValue, BorderLayout.CENTER);
    }

    @Override
    void initForm() {
        addLeftComponent(new JLabel("Food"), 1);
        addRightComponent(nameField, 1);

        addLeftComponent(new JLabel("Quantity"), 2);
        addRightComponent(quantityPanel, 2);

        addSubmitButton(e -> dispose());
    }
}
