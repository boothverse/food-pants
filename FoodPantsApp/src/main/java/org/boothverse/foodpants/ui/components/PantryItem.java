package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import java.awt.*;

public class PantryItem extends StandardItem {
    protected int WIDTH = 250;
    protected int HEIGHT = 50;

    public PantryItem(String name, int amt) {
        super(new Food("1", name, FoodGroup.FRUIT, null).createInstance(Quantities.getQuantity(amt, Units.GRAM)));

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
