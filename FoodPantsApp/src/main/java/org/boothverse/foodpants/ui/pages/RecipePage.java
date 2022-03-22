package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.forms.RecipeForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipePage extends Page implements ActionListener {
    private static final String[] labels = {"+", "Recommend", "Nutrition", "Search"};

    public RecipePage() {
        super(labels);

        for (String label : labels) {
            sideMenu.buttonMap.get(label).setActionCommand(label);
            sideMenu.buttonMap.get(label).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                new RecipeForm().setVisible(true);
                break;
            case "Recommend":
            case "Nutrition":
            case "Search":
                break;
        }
    }
}
