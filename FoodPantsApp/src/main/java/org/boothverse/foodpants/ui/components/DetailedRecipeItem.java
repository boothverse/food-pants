package org.boothverse.foodpants.ui.components;

import lombok.NonNull;
import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.*;

public class DetailedRecipeItem extends RecipeItem {
    protected JButton makeRecipeButton;
    protected JButton editRecipeButton;

    public DetailedRecipeItem(@NonNull Recipe r) {
        super(r);
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
        editRecipeButton = new JButton("Edit Recipe");

        JPanel spacer = new JPanel();
        spacer.setBackground(getBackground());
        contentPanel.addRightComponent(spacer, ++numRows);
        contentPanel.addMiddleComponent(new JSeparator(), ++numRows);
        contentPanel.addRightComponent(spacer, ++numRows);
        contentPanel.addRightComponent(makeRecipeButton, ++numRows);
        contentPanel.addRightComponent(editRecipeButton, ++numRows);



    }
}
