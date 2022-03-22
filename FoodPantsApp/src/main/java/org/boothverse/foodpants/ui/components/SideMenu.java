package org.boothverse.foodpants.ui.components;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SideMenu extends JPanel {
    public Map<String, SideMenuButton> buttonMap;

    public SideMenu(String[] labels) {
        super();
        setLayout(new GridLayout(labels.length, 1));
        buttonMap = new HashMap<>();

        initButtons(labels);
    }

    private void initButtons(String[] labels) {
        for (String label: labels) {
            SideMenuButton button = new SideMenuButton(label);
            if (label.length() >= 9) {
                button.setFont(button.getFont().deriveFont(10f));
            }

            buttonMap.put(label, button);
            add(button);
        }
    }
}
