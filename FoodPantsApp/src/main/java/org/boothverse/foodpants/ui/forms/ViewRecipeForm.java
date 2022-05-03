package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.components.DetailedRecipeItem;
import org.boothverse.foodpants.ui.components.RecipeItem;

import javax.swing.*;
import java.awt.*;

public class ViewRecipeForm extends StandardForm {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 600;
    DetailedRecipeItem recipeItem;
    Recipe recipe;

    public ViewRecipeForm(Recipe recipe, Component parent) {
        super(recipe.getName(), parent);
        initFormHeader("");
        this.recipeItem = new DetailedRecipeItem(recipe);
        this.recipe = recipeItem.getRecipe();
        setSize(WIDTH, HEIGHT);
        initSwing();
        initForm();
    }

    private void initSwing() {
        wrapperPanel.add(new JScrollPane(recipeItem));
    }

    @Override
    void initForm() {

    }
}
