package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;

import javax.swing.*;

public class AddFoodForm extends StandardForm {
    protected Food food;

    protected JTextField nameField;
    protected JComboBox<String> foodGroupBox;
    protected JPanel nutritionPanel;

    public AddFoodForm() {
        super("Add Food");
        initSwing();
        initForm();
    }

    private void initSwing() {
        nameField = new JTextField(30);
        foodGroupBox = new JComboBox<>(Services.FOOD_SERVICE.getFoodGroupOptions(FoodGroup.class));
    }

    @Override
    void initForm() {
        int i = 0;

        addLeftComponent(new JLabel("Name"), ++i);
        addRightComponent(nameField, i);

        addLeftComponent(new JLabel("Food Group"), ++i);
        addRightComponent(foodGroupBox, i);
    }
}
