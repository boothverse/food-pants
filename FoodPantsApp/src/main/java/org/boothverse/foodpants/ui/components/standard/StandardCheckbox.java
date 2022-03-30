package org.boothverse.foodpants.ui.components.standard;

import org.boothverse.foodpants.ui.ImageIconGenerator;
import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.*;

public class StandardCheckbox extends JCheckBox {
    Icon unselected;
    Icon selected;

    public StandardCheckbox() {
        super();

        // Override default press and focus operations
        super.setContentAreaFilled(false);
        setFocusPainted(false);

        resizeCheckbox();

        // Set default colors
        setForeground(Style.TRANSPARENT);
    }

    private void resizeCheckbox() {
        unselected = ImageIconGenerator.unselected;
        selected = ImageIconGenerator.selected;
        setIcon(unselected);
        setSelectedIcon( selected );
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
