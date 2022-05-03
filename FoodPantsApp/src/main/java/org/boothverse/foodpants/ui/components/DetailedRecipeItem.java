package org.boothverse.foodpants.ui.components;

import lombok.NonNull;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.controllers.RecipeController;
import org.boothverse.foodpants.ui.forms.CookedRecipeForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DetailedRecipeItem extends RecipeItem implements ActionListener {
    protected JButton makeRecipeButton;
    protected JButton editRecipeButton;
    private RecipeController recipeController = new RecipeController();
    private Recipe recipe;

    public DetailedRecipeItem(@NonNull Recipe r) {
        super(r);
        recipe = r;
        ingredientPanel.removeAll();
        ingredientDisplays.forEach(display -> ingredientPanel.add(display));

        contentPanel.remove(seeMoreWrapper);
        contentPanel.revalidate();
        contentPanel.repaint();

        contentPanel.addLeftComponent(new JLabel("Instructions"), ++numRows);

        TextArea instructionArea = new TextArea(recipe.getInstructions());
        instructionArea.setEditable(false);
        instructionArea.setBackground(getBackground());
        contentPanel.addRightComponent(instructionArea, numRows);

        makeRecipeButton = new JButton("Make Recipe");
        makeRecipeButton.addActionListener(this);
        editRecipeButton = new JButton("Edit Recipe");
        editRecipeButton.addActionListener(this);

        JPanel spacer = new JPanel();
        spacer.setBackground(getBackground());
        contentPanel.addRightComponent(spacer, ++numRows);
        contentPanel.addMiddleComponent(new JSeparator(), ++numRows);
        contentPanel.addRightComponent(spacer, ++numRows);
        contentPanel.addRightComponent(makeRecipeButton, ++numRows);
        contentPanel.addRightComponent(editRecipeButton, ++numRows);



    }

    public void makeRecipe(Recipe recipe) {
        ArrayList<FoodInstance> items = (ArrayList<FoodInstance>) recipeController.getMissingItems(recipe);
        if (items.size() > 0) {
            Object[] options = { "Add Missing To Cart", "Make Recipe" };
            int result = JOptionPane.showOptionDialog(null,
                "You're missing some ingredients.", "Missing Ingredients",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

            switch (result) {
                case 0:
                    try {
                        recipeController.addMissingIngredientsToCart(recipe.getId());
                    }
                    catch (PantsNotFoundException e) {
                    }
                    break;
                case 1:
                    recipeController.addItemsToPantry(items);
                    CookedRecipeForm form = new CookedRecipeForm("Cooked Recipe", this, recipe, false);
                    form.setLocationRelativeTo(this);
                    form.setVisible(true);
                    break;
                default:
                    System.out.println("none");
            }

        }
        else {
            CookedRecipeForm form = new CookedRecipeForm("Cooked Recipe", this, recipe, true);
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        }
    }

    public void editRecipe(Recipe recipe) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Make Recipe":
                makeRecipe(recipe);
                break;
            case "Edit Recipe":
                editRecipe(recipe);

        }
    }
}
