package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.components.standard.StandardItem;

import java.awt.*;

public class PantryItem extends StandardItem {
    protected int WIDTH = 350;
    protected int HEIGHT = 50;

    public PantryItem(FoodInstance food) {
        super(food);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
