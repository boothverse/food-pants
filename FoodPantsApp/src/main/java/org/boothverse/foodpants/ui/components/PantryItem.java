package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.forms.ConsumeForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;

public class PantryItem extends StandardItem {
    protected int WIDTH = 350;
    protected int HEIGHT = 50;

    private JButton pantryButton;

    public PantryItem(FoodInstance food) {
        super(food);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        pantryButton = new JButton("Eat");
        pantryButton.setVisible(true);
        pantryButton.addActionListener(e -> {
            StandardForm form = new ConsumeForm("Consume Item", PageManager.getActivePage(), getFoodInstance());
            form.setLocationRelativeTo(PageManager.getActivePage());
            form.setVisible(true);
        });

        pantryButton.setPreferredSize(new Dimension(40, 40));
        pantryButton.setMargin(new Insets(2,2,2,2));
        pantryButton.setFont(pantryButton.getFont().deriveFont(12f));
        rightFormat.add(pantryButton, BorderLayout.WEST);
    }

    @Override
    public void setModification(boolean status) {
        editButton.setVisible(status);
        deleteButton.setVisible(status);
        pantryButton.setVisible(!status);
    }
}
