package org.boothverse.foodpants.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMenu extends JPanel implements ActionListener {
    public PanelMenu(String[] labels) {
        super();
        setLayout(new GridLayout(labels.length, 1));
        initButtons(labels);
    }

    private void initButtons(String[] labels) {
        for (String label: labels) {
            PanelButton button = new PanelButton(label);
            if (label.length() >= 9) {
                button.setFont(button.getFont().deriveFont(10f));
            }
            add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
