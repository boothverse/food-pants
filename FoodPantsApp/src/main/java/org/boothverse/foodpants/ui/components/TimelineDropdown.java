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

class ComboKeyValue {
    private String key;
    private String value;

    public ComboKeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString(){
        return key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

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

                String comboValue = food.getName() + "(" + item.getQuantity().toString() + ")";
                addItem(new ComboKeyValue(comboValue, String.valueOf(++row)));
            } catch (PantsNotFoundException e) {};
        }
    }
}