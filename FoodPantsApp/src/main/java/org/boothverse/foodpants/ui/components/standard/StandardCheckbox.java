package org.boothverse.foodpants.ui.components.standard;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableCellRenderer;
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
        // Icons are placeholders rn, ideally these would be smaller (30px,30px) and look less bad lol
        unselected = new ImageIcon("src/main/resources/icons/uncheck.png");
        selected = new ImageIcon("src/main/resources/icons/check.png");
        setIcon(unselected);
        setSelectedIcon( selected );
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
