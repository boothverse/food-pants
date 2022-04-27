package org.boothverse.foodpants.ui.components.standard;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class StandardPanel extends JPanel {

    public StandardPanel() {
        super();
        setForeground(Style.BLACK);
        setBackground(Style.PLATINUM.brighter());
    }
}
