package org.boothverse.foodpants.ui.components.standard;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;

public class StandardLabel extends JLabel {
    public StandardLabel(String label) {
        super(label);

        setFont(Style.bodyStyle);
    }
}
