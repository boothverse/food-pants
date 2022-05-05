package org.boothverse.foodpants.ui.components;

import lombok.NonNull;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.components.standard.StandardItem;

import java.awt.*;
import java.awt.event.ActionListener;

public class IngredientItem extends StandardItem {
    private static int WIDTH = 350;
    private static int HEIGHT = 30;

    public IngredientItem(@NonNull FoodInstance foodInstance) {
        super(foodInstance);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        quantityLabel.setPreferredSize(new Dimension(40, 20));
        remove(editButton);

        for (ActionListener a : deleteButton.getActionListeners()) {
            deleteButton.removeActionListener(a);
        }

        deleteButton.addActionListener(e -> firePropertyChange("deleteItem", this, null));
    }

    @Override
    public void setModification(boolean status) {
        deleteButton.setVisible(status);
    }
}
