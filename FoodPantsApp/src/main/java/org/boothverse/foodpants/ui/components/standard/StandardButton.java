package org.boothverse.foodpants.ui.components.standard;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.*;

public class StandardButton extends JButton {
    public StandardButton(String label) {
        super(label);

        // Override default press and focus operations
        super.setContentAreaFilled(false);
        setFocusPainted(false);

        // Set default colors
        setBackground(Style.RED);
        setForeground(Style.PLATINUM);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(getBackground().brighter());
        } else {
            g.setColor(getBackground());
        }

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}