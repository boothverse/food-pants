package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;

import java.awt.*;

public class SideMenuButton extends StandardButton {
    public static final Dimension MAX_SIZE = new Dimension(80, 80);
    
    public SideMenuButton(String label) {
        super(label);

        // Setup default colors
        setBackground(Style.GREY_1);
        setForeground(Style.PLATINUM);
        setPreferredSize(MAX_SIZE);
    }

}
