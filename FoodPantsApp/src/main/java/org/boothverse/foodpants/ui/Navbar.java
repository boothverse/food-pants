package org.boothverse.foodpants.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navbar extends JPanel implements ActionListener {
    protected static final String[] navButtons = {"Pantry", "Recipes", "Nutrition", "Shopping List"};

    Navbar() {
        setLayout(new GridLayout(4,1));
        initButtons();
    }

    private void initButtons() {
        for (String label : navButtons) {
            NavButton navButton = new NavButton(label);
            add(navButton);
            navButton.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Component c : getComponents()) {
            c.setBackground(Style.RED);
        }
        ((NavButton) e.getSource()).setBackground(Style.PRESS_BUTTON);
    }
}
