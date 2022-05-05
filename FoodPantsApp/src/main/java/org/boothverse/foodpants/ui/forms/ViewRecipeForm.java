package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.components.DetailedRecipeItem;
import org.boothverse.foodpants.ui.components.RecipeItem;
import org.boothverse.foodpants.ui.components.standard.Notifiable;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ViewRecipeForm extends StandardForm implements Notifiable {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    DetailedRecipeItem recipeItem;
    Recipe recipe;

    public ViewRecipeForm(Recipe recipe, Component parent) {
        super(recipe.getName(), parent);
        initFormHeader("");
        this.recipeItem = new DetailedRecipeItem(recipe, this);
        this.recipe = recipeItem.getRecipe();
        setSize(WIDTH, HEIGHT);
        initForm();
    }

    @Override
    void initForm() {
        JPanel recipeViewer = new JPanel(new BorderLayout());
        recipeViewer.add(recipeItem, BorderLayout.CENTER);
        JScrollPane recipeScroller = new JScrollPane(recipeViewer);
        recipeScroller.setPreferredSize(new Dimension(WIDTH - 20, HEIGHT));
        recipeItem.setPreferredSize(new Dimension(WIDTH - 20, HEIGHT));
        wrapperPanel.add(recipeScroller);
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        if (Objects.equals(message, "edit")) {
            dispose();
        }
    }
}
