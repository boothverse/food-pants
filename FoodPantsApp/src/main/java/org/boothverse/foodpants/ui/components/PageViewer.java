package org.boothverse.foodpants.ui.components;

import javax.swing.*;

public class PageViewer extends JScrollPane {
    protected JPanel view;

    public PageViewer(JPanel view) {
        super(view);
        this.view = view;
        setVisible(true);
        setOpaque(true);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
}
