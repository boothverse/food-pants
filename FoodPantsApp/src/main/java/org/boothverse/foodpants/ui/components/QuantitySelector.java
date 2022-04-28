package org.boothverse.foodpants.ui.components;

import lombok.Getter;
import lombok.Setter;
import systems.uom.unicode.CLDR;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

@Getter
public class QuantitySelector extends JPanel {
    public static final String[] quantityOptions = {"unit", "g", "kg", "oz", "lb", "fl oz", "cup", "gallon", "l"};
    public static final Unit<?>[] unitClasses = {null, Units.GRAM, Units.KILOGRAM, CLDR.OUNCE, CLDR.POUND,
        CLDR.FLUID_OUNCE, CLDR.CUP, CLDR.GALLON, Units.LITRE};
    protected JComboBox<String> quantityUnitBox;
    protected JFormattedTextField quantityValueField;

    public QuantitySelector() {
        super(new BorderLayout());
        quantityUnitBox = new JComboBox<>(quantityOptions);
        quantityValueField = new JFormattedTextField(NumberFormat.getNumberInstance());

        // Use set preferred size if you want GridBagLayout to actually listen to what you want and not
        // ignore you like the garbage swing layout manager that it is.
        add(quantityUnitBox, BorderLayout.WEST);
        add(quantityValueField, BorderLayout.CENTER);
    }

    public QuantitySelector(String[] quantityOptions) {
        this();
        quantityUnitBox.removeAllItems();
        for (String s : quantityOptions) {
            quantityUnitBox.addItem(s);
        }
    }
}
