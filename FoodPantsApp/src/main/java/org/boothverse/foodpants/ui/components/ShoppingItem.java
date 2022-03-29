package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardCheckbox;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ShoppingItem extends StandardPanel {
    protected JLabel itemName;
    protected JTextField quantity;
    protected StandardCheckbox checkBox;


    public ShoppingItem(String name, int quantity) {
        super();
        setLayout(new BorderLayout());
        initChildren(name, quantity);
        setPreferredSize(new Dimension(400, 60));
    }

    private void initChildren(String name, int amt) {
        // Setup Checkbox
        checkBox = new StandardCheckbox();
        add(checkBox, BorderLayout.WEST);

        // Setup Name of the item
        itemName = new JLabel(name);
        itemName.setFont(Style.bodyStyle);
        add(itemName);

        // Set up quantity text field
        quantity = new JFormattedTextField(NumberFormat.getIntegerInstance());
        quantity.setText(amt + "");
        quantity.setFont(Style.bodyStyle);
        quantity.setHorizontalAlignment(JLabel.RIGHT);
        quantity.setBackground(Style.GREY_6);
        quantity.setPreferredSize(new Dimension(40, 40));
        add(quantity, BorderLayout.EAST);
    }

    public StandardCheckbox getCheckBox() {
        return checkBox;
    }
}
