package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.forms.RecipeForm;

import java.awt.event.ActionEvent;

public class RecipePage extends Page {
    private static final String[] labels = {"+", "Recommend", "Nutrition", "Search"};

    public RecipePage() {
        super(labels);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                new RecipeForm("Create Recipe").setVisible(true);
                break;
            case "Recommend":
            case "Nutrition":
            case "Search":
                break;
        }
    }
}
