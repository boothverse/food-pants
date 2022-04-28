package org.boothverse.foodpants.ui.components;


import org.boothverse.foodpants.ui.components.standard.StandardLabel;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;


import javax.swing.*;
import java.awt.*;

public class GoalItem extends StandardPanel {
    protected JLabel itemName;
    protected JLabel quantity;

    protected int WIDTH = 250;
    protected int HEIGHT = 50;

    public GoalItem(String nutrition, double value, boolean goalType) {
        super();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initChildren(nutrition, value);
    }

    private void initChildren(String name, double amt) {
        JPanel panel = new JPanel();

        itemName = new StandardLabel(name);
        quantity = new StandardLabel(amt + "");

        panel.add(quantity);

        add(itemName, BorderLayout.WEST);
        add(panel, BorderLayout.EAST);
    }
}
