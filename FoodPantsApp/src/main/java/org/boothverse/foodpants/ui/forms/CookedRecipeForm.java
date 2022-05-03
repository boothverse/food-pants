package org.boothverse.foodpants.ui.forms;


import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.controllers.RecipeController;
import org.boothverse.foodpants.ui.pages.Page;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class CookedRecipeForm extends StandardForm {

    JFormattedTextField servingsConsumedPanel;
    JFormattedTextField servingsLeftoverPanel;

    JButton confirm;
    Recipe recipe;
    boolean using;


    protected final int WIDTH = 640;
    protected final int HEIGHT = 480;


    public CookedRecipeForm(String header, Component parent) {
        super(header, parent);
        setSize(WIDTH, HEIGHT);

        initSwing();
        initForm();
    }
    public CookedRecipeForm(String header, Component parent, Recipe r, boolean b) {
        super(header, parent);
        setSize(WIDTH, HEIGHT);

        recipe = r;
        using = b;
        initSwing();
        initForm();
    }

    private void initSwing() {

        confirm = new JButton("Confirm");

        servingsConsumedPanel = new JFormattedTextField(NumberFormat.getNumberInstance());
        servingsLeftoverPanel = new JFormattedTextField(NumberFormat.getNumberInstance());

        assert(parent.getClass().equals(Page.class));
    }

    @Override
    void initForm() {
        int i = 0;
        addLeftComponent(new JLabel("Servings Consumed:"), ++i);
        addRightComponent(servingsConsumedPanel, i);

        addLeftComponent(new JLabel("Servings Leftover:"), ++i);
        addRightComponent(servingsLeftoverPanel, i);


        addSubmitButton(e -> {
            if (!servingsConsumedPanel.getText().isEmpty() && !servingsLeftoverPanel.getText().isEmpty()) {

                RecipeController recipeController = new RecipeController();
                try {
                    recipeController.produceCookedRecipe(recipe.getId(), using,
                        Double.parseDouble(servingsConsumedPanel.getText().replaceAll(",", "")),
                        Double.parseDouble(servingsLeftoverPanel.getText().replaceAll(",", "")));
                } catch (PantsNotFoundException ex) {
                    ex.printStackTrace();
                }

                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Must enter servings consumed and leftover.",
                    "Error: Empty Fields", JOptionPane.ERROR_MESSAGE);
            }
        });


    }

}
