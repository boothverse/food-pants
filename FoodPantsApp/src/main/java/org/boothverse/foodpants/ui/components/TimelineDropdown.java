package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.ui.controllers.FoodController;
import org.boothverse.foodpants.ui.controllers.NutritionController;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class TimelineDropdown extends JComboBox {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 30;

    int row;

    public TimelineDropdown(String[] items) {
        super(items);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        row = 0;
    }

    public void addNutritionalTime(Date startDate, Date endDate) {
        NutritionController nutritionController = new NutritionController();
        FoodController foodController = new FoodController();

        // Get nutritional items in select hours
        List<NutritionInstance> items = nutritionController.getItems(startDate, endDate);

        for (NutritionInstance item : items) {
            try {
                Food food = foodController.getFood(item.getFoodId());

                JLabel foodLabel = new JLabel(food.getName());
                JLabel quantityLabel = new JLabel(item.getQuantity().toString());

                add(foodLabel, ++row);
                add(quantityLabel, ++row);
            } catch (PantsNotFoundException e) {};
        }

        this.revalidate();
        this.repaint();
    }
}