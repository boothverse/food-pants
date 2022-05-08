package org.boothverse.foodpants.ui.components;

import lombok.Getter;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navbar extends JPanel implements ActionListener {
    public static final String[] PAGES = {"Pantry", "Recipes", "Nutrition", "Shopping List"};

    @Getter
    public static JButton[] navButtons;

    public Navbar() {
        setLayout(new GridLayout(PAGES.length, 1));
        initButtons();
    }

    private void initButtons() {
        navButtons = new JButton[4];
        int i = 0;
        for (String label : PAGES) {
            StandardButton navButton = new StandardButton(label);
            add(navButton);
            navButtons[i] = navButton;
            i++;

            // Initialize pantry button to selected
            if (label.equals("Pantry")) {
                navButton.setBackground(Style.PRESS_NAV_BUTTON);
            }

            navButton.addActionListener(this);
            navButton.setActionCommand(label);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button color change
        for (Component c : getComponents()) {
            c.setBackground(Style.PRIMARY);
        }
        ((StandardButton) e.getSource()).setBackground(Style.PRESS_NAV_BUTTON);

        // Handle page change logic
        changePage(e.getActionCommand());
    }

    private void changePage(String pageName) {
        PageManager.setPage(pageName);
    }
}
