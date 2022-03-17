package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.*;

public class ListItem extends JPanel {
    protected JLabel itemName;
    protected JLabel quantity;
    protected JCheckBox checkBox;

    public ListItem(String name, int quantity) {
        super();
        setBackground(Style.WHITE);
        setForeground(Style.BLACK);

        initChildren(name, quantity);
    }

    private void initChildren(String name, int amt) {
        checkBox = new JCheckBox();
        add(checkBox);
        itemName = new JLabel(name);
        add(itemName);
        quantity = new JLabel(amt + "");
        add(quantity);
    }

}
