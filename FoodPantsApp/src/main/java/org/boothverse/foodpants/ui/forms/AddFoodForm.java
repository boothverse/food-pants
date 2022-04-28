package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionType;
import org.boothverse.foodpants.ui.components.QuantitySelector;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddFoodForm extends StandardForm {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 700;

    protected Food food;

    protected JTextField nameField;
    protected JComboBox<String> foodGroupBox;
    protected static List<JLabel> nutritionLabels;
    protected QuantitySelector[] nutritionQuantitySelectors;

    static {
        String[] nutritionNames = Services.FOOD_SERVICE.getEnumOptions(NutritionType.class);
        nutritionLabels = new ArrayList<>();
        for (String s : nutritionNames) {
            s = s.replaceAll("_", " ");
            s = s.charAt(0) + s.toLowerCase().substring(1);
            nutritionLabels.add(new JLabel(s));
        }
    }

    public AddFoodForm() {
        super("Add Food");
        initSwing();
        initForm();
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    private void initSwing() {
        nutritionQuantitySelectors = new QuantitySelector[nutritionLabels.size()];
        nameField = new JTextField(30);
        foodGroupBox = new JComboBox<>(Services.FOOD_SERVICE.getEnumOptions(FoodGroup.class));


    }

    @Override
    void initForm() {
        int i = 0;

        addLeftComponent(new JLabel("Name"), ++i);
        addRightComponent(nameField, i);


        addLeftComponent(new JLabel("Food Group"), ++i);
        addRightComponent(foodGroupBox, i);

        addRightComponent(new JLabel(), ++i);       // Empty JPanel to space

        int j = 0;
        for (JLabel label: nutritionLabels) {
            if (j == 0) {
                nutritionQuantitySelectors[j] = new QuantitySelector(new String[]{"unit"});
            }
            else {
                nutritionQuantitySelectors[j] = new QuantitySelector(new String[]{"g"});
            }

            addLeftComponent(label, ++i);
            addRightComponent(nutritionQuantitySelectors[j], i);
            j++;
        }

        addRightComponent(new JLabel(), ++i);       // Empty JPanel to space
        addRightComponent(new JLabel(), ++i);       // Empty JPanel to space

        addSubmitButton(e -> dispose());
    }
}
