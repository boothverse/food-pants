package org.boothverse.foodpants.ui.components;

import lombok.Getter;
import systems.uom.unicode.CLDR;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Objects;

@Getter
public class QuantitySelector extends JPanel {
    public static final String[] quantityOptions = {"unit", "g", "kg", "oz", "lb", "fl oz", "cup", "gallon", "L", "calorie"};
    public static final Unit<?>[] unitClasses = {AbstractUnit.ONE, Units.GRAM, Units.KILOGRAM, CLDR.OUNCE, CLDR.POUND,
        CLDR.FLUID_OUNCE, CLDR.CUP, CLDR.GALLON, Units.LITRE, CLDR.CALORIE};

    protected JComboBox<String> quantityUnitBox;
    protected JFormattedTextField quantityValueField;

    public QuantitySelector() {
        super(new BorderLayout());
        quantityUnitBox = new JComboBox<>(quantityOptions);
        quantityValueField = new JFormattedTextField(NumberFormat.getNumberInstance());

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

    public Unit<?> getSelectedUnit() {
        return unitClasses[quantityUnitBox.getSelectedIndex()];
    }

    public Quantity<?> getSelectedQuantity() {
        Unit<?> unit = unitClasses[quantityUnitBox.getSelectedIndex()];
        String enteredText = quantityValueField.getText();
        double quantityVal;

        if (!Objects.equals(enteredText, "")) {
            quantityVal = Double.parseDouble(enteredText);
        }
        else {
            quantityVal = 0.0;
        }
        return Quantities.getQuantity(quantityVal, unit);
    }

    public boolean isEmpty() {
        return Objects.equals(quantityValueField.getText(), "");
    }
}
