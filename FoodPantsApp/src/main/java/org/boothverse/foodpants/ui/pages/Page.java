package org.boothverse.foodpants.ui.pages;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.PanelMenu;

import javax.swing.*;
import java.awt.*;

public class Page extends JPanel {
    protected PanelMenu buttons;
    protected JPanel buttonPane;

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
        buttonPane = new JPanel();
        buttonPane.setLayout(new BorderLayout());
        buttonPane.setBackground(Style.TRANSPARENT);

        // Add buttons to the top-right side of the screen
        add(buttonPane, BorderLayout.NORTH);
        buttons = new PanelMenu(buttonLabels);
        buttonPane.add(buttons, BorderLayout.EAST);
    }

}
