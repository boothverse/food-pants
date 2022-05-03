package org.boothverse.foodpants.ui.components;

import lombok.NonNull;
import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.*;

public class DetailedRecipeItem extends RecipeItem {
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
    }
}
