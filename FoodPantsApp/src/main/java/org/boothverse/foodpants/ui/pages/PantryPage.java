package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.forms.AddPantryForm;

import java.awt.event.ActionEvent;

public class PantryPage extends Page {
    private static final String[] labels = {"+", "Edit", "Search"};

    public PantryPage() {
        super(labels);
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
