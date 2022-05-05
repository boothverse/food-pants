package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.components.FoodSearchBar;
import org.boothverse.foodpants.ui.components.IngredientItem;
import org.boothverse.foodpants.ui.components.ItemList;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.controllers.FoodController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class EditRecipeForm extends AddRecipeForm {
    private Recipe recipe;
    private FoodController foodController = new FoodController();
    public EditRecipeForm(String header, Component parent, Recipe recipe) {
        super(header, parent);
        this.recipe = recipe;

        try {
            foodSearchBar.setSelectedItem(foodController.getFood(recipe.getId()));
            servingsPanel.setText(recipe.getServings().toString());
            instructionArea.setText(recipe.getInstructions());
            for (FoodInstance food : recipe.getIngredients()) {
                ingredients.add(new IngredientItem(food));
            }
            ingredients.setModifiable(true);
        }
        catch (PantsNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        if (Objects.equals(message, "remove")) {
            revalidate();
            repaint();
        }
    }
}
