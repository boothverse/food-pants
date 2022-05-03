package org.boothverse.foodpants.ui.components;

import javax.swing.*;
import java.awt.*;

public class TimelineDropdown extends JComboBox {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 30;

    int row;

    public TimelineDropdown(String[] items) {
        super(items);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        row = 0;
    }
}