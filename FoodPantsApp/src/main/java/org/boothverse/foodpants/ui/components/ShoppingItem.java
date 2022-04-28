package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.components.standard.StandardItem;

import javax.swing.*;
import java.awt.*;

public class ShoppingItem extends StandardItem {
    protected JCheckBox checkBox;

    public ShoppingItem(FoodInstance foodInstance) {
        super(foodInstance);

        // Setup Checkbox
        checkBox = new JCheckBox();
        add(checkBox, BorderLayout.WEST);
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }
}
