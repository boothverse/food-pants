package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.components.standard.ItemList;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardLabel;
import org.boothverse.foodpants.ui.controllers.RecipeController;

import javax.swing.*;
import java.awt.*;

public class RecipeForm extends StandardItemForm {
    protected final int WIDTH = 640;
    protected final int HEIGHT = 480;

    protected RecipeController recipeController;

    public RecipeForm(String header, Component parent) {
        super(header, parent);
        setSize(WIDTH, HEIGHT);

        recipeController = new RecipeController();

        textLabels = new String[]{"Name", "Food Group", "Nutrition",
            "Instructions", "Ingredients", "Servings"};
    }

    @Override
    void initForm() {
        int i;
        for (i = 0; i < textLabels.length; i++) {
            JLabel lbl = new StandardLabel(textLabels[i]);
            JTextField txtField = new JTextField(TXT_FIELD_WIDTH);

            addLeftComponent(lbl, i + 1);
            addRightComponent(txtField, i + 1);
        }

        JLabel ingredients = new StandardLabel("Ingredients");
        TextArea ingredArea = new TextArea();

        addLeftComponent(ingredients, ++i);
        addRightComponent(ingredArea, ++i);

        JButton submitBtn = new StandardButton("Submit");
        submitBtn.addActionListener(e -> {
            this.dispose();
        });

        addRightComponent(submitBtn, ++i);
    }
}
