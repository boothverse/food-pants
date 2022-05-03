package org.boothverse.foodpants.ui.components;

import lombok.NonNull;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import org.boothverse.foodpants.ui.controllers.FoodController;
import org.boothverse.foodpants.ui.controllers.NutritionController;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class NutritionItem extends StandardPanel {
    protected NutritionInstance nutritionInstance;

    protected int WIDTH = 250;
    protected int HEIGHT = 50;

    public NutritionItem(@NonNull NutritionInstance nutritionInstance) throws PantsNotFoundException {
        super();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.nutritionInstance = nutritionInstance;
        initComponents();
    }

    protected void initComponents() throws PantsNotFoundException {
        FoodController foodController = new FoodController();

        String name = foodController.getFood(nutritionInstance.getFoodId()).getName();
        Number amt = nutritionInstance.getQuantity().getValue();

        JPanel panel = new JPanel();
        panel.setBackground(Style.TRANSPARENT);
        add(panel, BorderLayout.EAST);

        // Setup Name of the item
        JLabel nameLabel = new JLabel(name);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        add(nameLabel);

        // Set up quantity text field
        JTextField quantityLabel = new JFormattedTextField(NumberFormat.getIntegerInstance());
        quantityLabel.setEditable(false);
        quantityLabel.setText(amt + "");
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);
        quantityLabel.setPreferredSize(new Dimension(40, 40));

        JLabel unitLabel = new JLabel("(" + nutritionInstance.getQuantity().getUnit().toString() + ")");
        unitLabel.setHorizontalAlignment(JLabel.LEFT);

        panel.add(unitLabel);
        panel.add(quantityLabel);
    }
}
