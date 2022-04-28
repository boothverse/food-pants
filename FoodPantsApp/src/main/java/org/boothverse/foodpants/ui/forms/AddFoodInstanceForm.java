package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.components.FoodSearchBar;
import systems.uom.unicode.CLDR;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class AddFoodInstanceForm extends StandardForm {
    protected final String[] quantityOptions = {"unit", "g", "kg", "oz", "lb", "fl oz", "cup", "gallon", "l"};
    protected final Unit<?>[] unitClasses = {null, Units.GRAM, Units.KILOGRAM, CLDR.OUNCE, CLDR.POUND,
                                            CLDR.FLUID_OUNCE, CLDR.CUP, CLDR.GALLON, Units.LITRE};

    FoodSearchBar foodSearchBar;
    JPanel quantityPanel;
    JComboBox<String> quantityUnitBox;
    JFormattedTextField quantityValueField;

    public AddFoodInstanceForm() {
        super("Add Food");

        initSwing();
        initForm();
    }

    private void initSwing() {
        foodSearchBar = new FoodSearchBar();
        foodSearchBar.populate(java.util.List.of(new String[]{"Apple", "Orange", "Banana"}));

        quantityPanel = new JPanel(new BorderLayout());
        quantityUnitBox = new JComboBox<>(quantityOptions);
        quantityValueField = new JFormattedTextField(NumberFormat.getNumberInstance());

        // Use set preferred size if you want GridBagLayout to actually listen to what you want and not
        // ignore you like the garbage swing layout manager that it is.
        quantityValueField.setPreferredSize(new Dimension(getWidth() / 2, quantityValueField.getHeight()));
        quantityPanel.add(quantityUnitBox, BorderLayout.WEST);
        quantityPanel.add(quantityValueField, BorderLayout.CENTER);
    }

    @Override
    void initForm() {
        addLeftComponent(new JLabel("Food"), 1);
        addRightComponent(foodSearchBar, 1);

        addLeftComponent(new JLabel("Quantity"), 2);
        addRightComponent(quantityPanel, 2);

        addSubmitButton(e -> dispose());
    }
}
