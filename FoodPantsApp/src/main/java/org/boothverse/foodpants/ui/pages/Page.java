package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.SideMenu;
import org.boothverse.foodpants.ui.components.standard.Notifiable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Page extends JPanel implements ActionListener, Notifiable {
    protected SideMenu sideMenu;
    protected JPanel sidePanel;

    public Page() {
        super();
        setVisible(true);
        setLayout(new BorderLayout());
    }

    public Page(String[] buttonLabels) {
        this();
        initButtons(buttonLabels);

        for (String label : buttonLabels) {
            sideMenu.buttonMap.get(label).setActionCommand(label);
            sideMenu.buttonMap.get(label).addActionListener(this);
        }
    }

    private void initButtons(String[] buttonLabels) {
        // Create invisible panel to be on the right side
        sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setBackground(Style.TRANSPARENT);

        // Add button panel to the top-right side of the screen
        add(sidePanel, BorderLayout.EAST);
        sideMenu = new SideMenu(buttonLabels);
        sidePanel.add(sideMenu, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
    }
}
