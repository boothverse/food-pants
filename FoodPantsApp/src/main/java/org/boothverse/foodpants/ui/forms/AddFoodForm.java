package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionDescriptor;
import org.boothverse.foodpants.persistence.NutritionType;
import org.boothverse.foodpants.ui.components.QuantitySelector;
import org.boothverse.foodpants.ui.controllers.FoodController;
import systems.uom.unicode.CLDR;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddFoodForm extends StandardForm implements ActionListener {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 700;

    protected Food food;

    protected JTextField nameField;
    protected JComboBox<String> foodGroupBox;
    protected static List<JLabel> nutritionLabels;
    protected QuantitySelector[] nutritionQuantitySelectors;
    protected AddFoodInstanceForm parent;

    private FoodController foodController;

    static {
        String[] nutritionNames = Services.NUTRITION_SERVICE.getNutritionTypes();
        nutritionLabels = new ArrayList<>();
        nutritionLabels.add(new JLabel("Serving Size"));
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
        foodController = new FoodController();
    }

    private void initSwing() {
        nutritionQuantitySelectors = new QuantitySelector[nutritionLabels.size()];
        nameField = new JTextField(30);
        foodGroupBox = new JComboBox<>(Services.FOOD_SERVICE.getFoodGroups());
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
            if (j ==0) {
                nutritionQuantitySelectors[j] = new QuantitySelector();
            }
            else if (j == 1) {
                nutritionQuantitySelectors[j] = new QuantitySelector(new String[]{"unit"});
                nutritionQuantitySelectors[j].setEnabled(false);
            }
            else {
                nutritionQuantitySelectors[j] = new QuantitySelector(new String[]{"g"});
                nutritionQuantitySelectors[j].setEnabled(false);
            }

            addLeftComponent(label, ++i);
            addRightComponent(nutritionQuantitySelectors[j], i);
            j++;
        }

        addRightComponent(new JLabel(), ++i);       // Empty JPanel to space
        addRightComponent(new JLabel(), ++i);       // Empty JPanel to space

        addSubmitButton(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] nutritionNames = Services.NUTRITION_SERVICE.getNutritionTypes();
        Map<NutritionType, Quantity<?>> nutritionMap = new HashMap<>();
        for (int i = 1; i < nutritionQuantitySelectors.length; i++) {
            double quantityVal = 0.0;
            String enteredText = nutritionQuantitySelectors[i].getQuantityValueField().getText();

            if (!Objects.equals(enteredText, "")) {
                quantityVal = Double.parseDouble(enteredText);
            }

            if (i == 1) {
                nutritionMap.put(NutritionType.valueOf(nutritionNames[i -1]),
                    Quantities.getQuantity(quantityVal, CLDR.CALORIE));
            }
            else {
                nutritionMap.put(NutritionType.valueOf(nutritionNames[i -1]),
                    Quantities.getQuantity(quantityVal, Units.GRAM));
            }
        }

        String servingText = nutritionQuantitySelectors[0].getQuantityValueField().getText();
        Unit<?> unit = nutritionQuantitySelectors[0].getSelectedUnit();
        double quantityVal = 0.0;

        if (!Objects.equals(servingText, "")) {
            quantityVal = Double.parseDouble(servingText);
        }

        NutritionDescriptor nut = new NutritionDescriptor(nutritionMap, Quantities.getQuantity(quantityVal, unit));
        Food newFood = foodController.addFood(nameField.getText(), FoodGroup.valueOf((String) foodGroupBox.getSelectedItem()), nut);

        parent.updateFoodSearchBar(newFood);
        dispose();
    }

    public void setFormToBeNotified(AddFoodInstanceForm a) {
        parent = a;
    }
}
