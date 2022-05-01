package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.RecipeItem;
import org.boothverse.foodpants.ui.forms.RecipeForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecipePage extends Page {
    private static final String[] labels = {"+", "Recommend", "Nutrition", "Search"};

    // TODO: add grandma cerny's synatactic sugar cookie recipe
    public RecipePage() {
        super(labels);

        JPanel listWrapper = new JPanel(new FlowLayout());
        JPanel recipeListPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        add(listWrapper);
        listWrapper.add(recipeListPanel);
        recipeListPanel.add(new RecipeItem(null));
        recipeListPanel.add(new RecipeItem(null));
        listWrapper.setBackground(Style.TRANSPARENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                RecipeForm form = new RecipeForm("Create Recipe");
                form.setLocationRelativeTo(this);
                form.setVisible(true);
                break;
            case "Recommend":
            case "Nutrition":
            case "Search":
                break;
        }
    }
}
