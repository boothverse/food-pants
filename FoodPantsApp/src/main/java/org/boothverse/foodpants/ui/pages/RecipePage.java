package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.RecipeItem;
import org.boothverse.foodpants.ui.controllers.RecipeController;
import org.boothverse.foodpants.ui.forms.AddRecipeForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class RecipePage extends Page {
    private static final String[] labels = {"+", "Recommend", "Nutrition", "Search"};

    protected JPanel listWrapper;
    protected JPanel recipeListPanel;
    protected RecipeController recipeController = new RecipeController();

    public RecipePage() {
        super(labels);

        listWrapper = new JPanel(new FlowLayout());
        recipeListPanel = new JPanel(new GridLayout(0, 1, 0, 10));

        add(listWrapper);
        listWrapper.add(recipeListPanel);

        updateList();

        listWrapper.setBackground(Style.TRANSPARENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                AddRecipeForm form = new AddRecipeForm("Create Recipe", this);
                form.setLocationRelativeTo(this);
                form.setVisible(true);
                break;
            case "Recommend":
            case "Nutrition":
            case "Search":
                break;
        }
    }

    protected void updateList() {
        List<Recipe> listItems = recipeController.getRecipes();

        recipeListPanel.removeAll();
        for (Recipe item : listItems) {
            RecipeItem thisItem = new RecipeItem(item);
            recipeListPanel.add(thisItem);
        }
        revalidate();
        repaint();
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        if (Objects.equals(message, "add recipe")) {
            recipeListPanel.add(new RecipeItem((Recipe) newValue));
            revalidate();
            repaint();
        }
        else if (Objects.equals(message, "remove")) {

        }
        else if (Objects.equals(message, "update")) {
            updateList();
        }
    }
}
