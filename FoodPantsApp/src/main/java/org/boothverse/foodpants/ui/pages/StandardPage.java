package org.boothverse.foodpants.ui.pages;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.PanelButton;
import org.boothverse.foodpants.ui.components.PanelMenu;

import javax.swing.*;
import java.awt.*;

public class StandardPage extends JPanel {
    protected static final String[] buttonLabels = {"Edit", "Export", "Mark All"};
    protected static PanelMenu buttons;

    public StandardPage() {
        super();
        setBackground(Style.PLATINUM);
        setVisible(true);
        setLayout(new BorderLayout());

        initButtons();
    }

    private void initButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BorderLayout());
        buttonPane.setBackground(getBackground());
        add(buttonPane, BorderLayout.EAST);
        buttons = new PanelMenu(buttonLabels);
        buttonPane.add(buttons, BorderLayout.NORTH);
    }

}
