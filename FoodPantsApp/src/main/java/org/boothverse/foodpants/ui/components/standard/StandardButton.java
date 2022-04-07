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

        // Construct Border
        setBorder(Style.BORDER_1);

        // Set Font
        setFont(Style.bodyStyle);
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