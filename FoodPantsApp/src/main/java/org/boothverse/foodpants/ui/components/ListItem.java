package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;

public class ListItem extends JPanel {
    protected JLabel itemName;
    protected JLabel quantity;
    protected JCheckBox checkBox;

    public ListItem(String name, int quantity) {
        super();
        setBackground(Style.WHITE);
        setForeground(Style.BLACK);
        setBorder(Style.BORDER_1);

        initChildren(name, quantity);
    }

    private void initChildren(String name, int amt) {
        checkBox = new StandardCheckbox();
        add(checkBox);
        itemName = new JLabel(name);
        itemName.setFont(Style.ARIAL);
        add(itemName);
        quantity = new JLabel(amt + "");
        quantity.setFont(Style.ARIAL);
        add(quantity);
    }

}
