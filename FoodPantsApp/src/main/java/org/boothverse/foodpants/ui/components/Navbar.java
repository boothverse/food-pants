package org.boothverse.foodpants.ui.components;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navbar extends JPanel implements ActionListener {
    public static final String[] PAGES = {"Pantry", "Recipes", "Nutrition", "Shopping List"};

    public Navbar() {
        setLayout(new GridLayout(PAGES.length,1));
        initButtons();
    }

    private void initButtons() {
        for (String label : PAGES) {
            StandardButton navButton = new StandardButton(label);
            add(navButton);
            navButton.addActionListener(this);
            navButton.setActionCommand(label);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button color change
        for (Component c : getComponents()) { c.setBackground(Style.RED); }
        ((StandardButton) e.getSource()).setBackground(Style.PRESS_NAV_BUTTON);

        // Handle page change logic
        changePage(e.getActionCommand());
    }

    private void changePage(String pageName) {
        PageManager.setPage(pageName);
    }
}
