package org.boothverse.foodpants.ui;

import javax.swing.*;

public class InterfacePanel extends JScrollPane {
    InterfacePanel(JPanel view) {
        super(view);
        setBackground(Style.PLATINUM);
        setVisible(true);
        setOpaque(true);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
}
