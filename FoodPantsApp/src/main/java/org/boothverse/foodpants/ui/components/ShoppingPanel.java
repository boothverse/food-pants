package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardCheckbox;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;

import javax.swing.*;

public class ShoppingPanel extends StandardPanel {

    protected JLabel itemName;
    protected JLabel quantity;
    protected JCheckBox checkBox;

    public ShoppingPanel(String name, int quantity) {
        super();
        initChildren(name, quantity);
    }

    private void initChildren(String name, int amt) {
        checkBox = new StandardCheckbox();
        add(checkBox);
        itemName = new JLabel(name);
        itemName.setFont(Style.bodyStyle);
        add(itemName);
        quantity = new JLabel(amt + "");
        quantity.setFont(Style.bodyStyle);
        add(quantity);
    }
}
