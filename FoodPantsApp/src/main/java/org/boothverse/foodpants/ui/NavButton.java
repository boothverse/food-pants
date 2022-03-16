package org.boothverse.foodpants.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NavButton extends JButton {
    NavButton(String label) {
        super(label);

        // Override default press and focus operations
        super.setContentAreaFilled(false);
        setFocusPainted(false);

        // Set default colors
        setBackground(Style.RED);
        setForeground(Style.PLATINUM);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                                                     BorderFactory.createEmptyBorder(5,5,5,5)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(Style.PRESS_BUTTON);
        }
        else {
            g.setColor(getBackground());
        }

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}