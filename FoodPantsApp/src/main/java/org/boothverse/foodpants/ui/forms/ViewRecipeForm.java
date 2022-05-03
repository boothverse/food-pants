package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.components.RecipeItem;

import javax.swing.*;
import java.awt.*;

public class ViewRecipeForm extends StandardForm {
    RecipeItem recipeItem;
    Recipe recipe;

    public ViewRecipeForm(Recipe recipe, Component parent) {
        super(recipe.getName(), parent);
        initFormHeader("");
        this.recipeItem = new RecipeItem(recipe);
        this.recipe = recipeItem.getRecipe();

        initSwing();
        initForm();
    }

    private void initSwing() {
        recipeItem.getIngredientPanel().removeAll();
        recipeItem.getIngredientDisplays().forEach(display -> recipeItem.getIngredientPanel().add(display));
        recipeItem.getContentPanel().remove(recipeItem.getSeeMoreWrapper());
        recipeItem.getContentPanel().revalidate();
        recipeItem.getContentPanel().repaint();
        add(recipeItem);
    }

    @Override
    void initForm() {
        int i = 0;

        addLeftComponent(new JLabel("Food Group"), i);
        //addRightComponent(, i);
    }
}
