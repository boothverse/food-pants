package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;

public class PageViewer extends JScrollPane {
    protected JPanel view;

    public PageViewer(JPanel view) {
        super(view);
        this.view = view;
        setBackground(Style.PLATINUM);
        setVisible(true);
        setOpaque(true);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public JPanel getPage() { return view; }
}
