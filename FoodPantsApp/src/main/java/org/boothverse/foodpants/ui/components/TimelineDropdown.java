package org.boothverse.foodpants.ui.components;

import javax.swing.*;
import java.awt.*;

public class TimelineDropdown extends JComboBox {
    private static final int WIDTH = 250;
    private static final int HEIGHT = 30;

    public TimelineDropdown(String[] items) {
        super(items);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}