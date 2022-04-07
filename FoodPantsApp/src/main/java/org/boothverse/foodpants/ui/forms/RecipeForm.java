package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardLabel;

import javax.swing.*;
import java.awt.*;

public class RecipeForm extends StandardForm {
    protected final int WIDTH = 640;
    protected final int HEIGHT = 480;
    protected final int TXT_FIELD_WIDTH = 25;

    public RecipeForm(String header) {
        super(header);
        setSize(WIDTH, HEIGHT);
    }

    @Override
    void initForm(final JPanel panel) {
        String[] textLabels = new String[]{"Name", "Servings"};

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
        submitBtn.addActionListener(e -> this.dispose());

        addRightComponent(submitBtn, ++i);

        panel.add(boxPanel, BorderLayout.CENTER);
    }
}
