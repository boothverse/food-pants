package org.boothverse.foodpants.ui.pages;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.SideMenu;

import javax.swing.*;
import java.awt.*;

public class Page extends JPanel {
    protected SideMenu sideMenu;
    protected JPanel sidePanel;

    public Page() {
        super();
        setBackground(Style.PLATINUM);
        setVisible(true);
        setLayout(new BorderLayout());
    }

    public Page(String[] buttonLabels) {
        this();
        initButtons(buttonLabels);
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

}
