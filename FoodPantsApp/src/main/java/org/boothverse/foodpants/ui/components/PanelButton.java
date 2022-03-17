package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.ui.Style;

import java.awt.*;

public class PanelButton extends StandardButton {
    public static final Dimension MAX_SIZE = new Dimension(40, 40);
    
    public PanelButton(String label) {
        super(label);

        // Setup default colors
        setBackground(Style.GREY_1);
        setForeground(Style.PLATINUM);
        setSize(MAX_SIZE);
    }

    
}
