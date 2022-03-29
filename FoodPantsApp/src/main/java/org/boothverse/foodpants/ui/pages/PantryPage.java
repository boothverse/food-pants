package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.components.standard.PantryPanel;
import org.boothverse.foodpants.ui.forms.AddPantryForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PantryPage extends Page {
    private static final String[] labels = {"+", "Search"};

    public PantryPage() {
        super(labels);

        JPanel listWrapper = new JPanel(new FlowLayout());

        JPanel panel = new JPanel(new GridLayout(2, 2));

        panel.add(new PantryPanel("Banana", 3));
        panel.add(new PantryPanel("Muffin", 1));
        panel.add(new PantryPanel("Apple", 2));

        listWrapper.add(panel);
        add(listWrapper);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                new AddPantryForm().setVisible(true);
                break;
            case "Edit":
            case "Search":
                break;
        }
    }
}
