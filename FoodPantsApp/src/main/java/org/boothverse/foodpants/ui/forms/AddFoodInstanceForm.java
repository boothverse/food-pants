package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.components.FoodSearchBar;
import systems.uom.unicode.CLDR;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

public class AddFoodInstanceForm extends StandardForm implements ItemListener {
    protected final String[] quantityOptions = {"unit", "g", "kg", "oz", "lb", "fl oz", "cup", "gallon", "l"};
    protected final Unit<?>[] unitClasses = {null, Units.GRAM, Units.KILOGRAM, CLDR.OUNCE, CLDR.POUND,
                                            CLDR.FLUID_OUNCE, CLDR.CUP, CLDR.GALLON, Units.LITRE};

    FoodSearchBar foodSearchBar;
    JPanel quantityPanel;
    JComboBox<String> quantityUnitBox;
    JFormattedTextField quantityValueField;
    JButton foodButton;
    boolean addFoodButton = false;

    public AddFoodInstanceForm() {
        super("Add Food");

        initSwing();
        initForm();
    }

    private void initSwing() {
        foodButton = new JButton("Edit Selected Food");
        foodSearchBar = new FoodSearchBar();
        foodSearchBar.addItemListener(this);
        foodSearchBar.populate(java.util.List.of(new String[]{"Apple", "Orange", "Banana"}));

        quantityPanel = new JPanel(new BorderLayout());
        quantityUnitBox = new JComboBox<>(quantityOptions);
        quantityValueField = new JFormattedTextField(NumberFormat.getNumberInstance());

        // Use set preferred size if you want GridBagLayout to actually listen to what you want and not
        // ignore you like the garbage swing layout manager that it is.
        quantityValueField.setPreferredSize(new Dimension(getWidth() / 2, quantityValueField.getHeight()));
        quantityPanel.add(quantityUnitBox, BorderLayout.WEST);
        quantityPanel.add(quantityValueField, BorderLayout.CENTER);

        foodButton.addActionListener(e -> {
            if (addFoodButton) {
                AddFoodForm form = new AddFoodForm();
                form.setLocationRelativeTo(this);
                form.setVisible(true);
            }
        });
    }

    @Override
    void initForm() {
        int i = 0;
        addLeftComponent(new JLabel("Food"), ++i);
        addRightComponent(foodSearchBar, i);


        addRightComponent(foodButton, ++i);
        addRightComponent(new JPanel(),++i);

        addLeftComponent(new JLabel("Quantity"), ++i);
        addRightComponent(quantityPanel, i);
        addRightComponent(new JPanel(),++i);

        addSubmitButton(e -> dispose());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Object item = e.getItem();
            System.out.println(item.toString());
            if (e.getItem().toString().equals("Create New Food")) {
                foodButton.setText("Create New Food");
                addFoodButton = true;
            }
            else {
                foodButton.setText("Edit Selected Food");
                addFoodButton = false;
            }
        }
    }
}
