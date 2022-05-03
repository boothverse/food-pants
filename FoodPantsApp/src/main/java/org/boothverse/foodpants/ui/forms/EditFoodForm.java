package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.business.services.util.UnitToString;
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditFoodForm extends AddFoodForm {
    private FoodController foodController;
    private Food food;

    EditFoodForm(Food editFood, Component parent) {
        super("Edit Food", parent);
        food = editFood;
        foodController = new FoodController();

        NutritionDescriptor nutritionDescriptor = editFood.getNutrition();
        Map<NutritionType, Quantity<?>> nutritionInfo = nutritionDescriptor.getNutritionInfo();

        nameField.setText(editFood.getName());
        foodGroupBox.setSelectedItem(editFood.getFoodGroup().name());
        NutritionType[] nutritionNames = NutritionType.values();

        int j = 0;
        int i = 0;

        for (QuantitySelector q : nutritionQuantitySelectors) {
            if (j == 0) {
                q.getQuantityUnitBox().setSelectedItem(UnitToString.convertUnitToString(nutritionDescriptor.getServingSize().getUnit()));
                q.getQuantityValueField().setValue(nutritionDescriptor.getServingSize().getValue());
                j++;
            }
            else {
                q.getQuantityValueField().setValue(nutritionInfo.get(nutritionNames[i]).getValue());
                q.getQuantityUnitBox().setSelectedItem(nutritionInfo.get(nutritionNames[i]).getUnit());
                i++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] nutritionNames = Services.NUTRITION_SERVICE.getNutritionTypes();
        Map<NutritionType, Quantity<?>> nutritionMap = new HashMap<>();
        for (int i = 1; i < nutritionQuantitySelectors.length; i++) {
            double quantityVal = 0.0;
            String enteredText = nutritionQuantitySelectors[i].getQuantityValueField().getText();
            enteredText = enteredText.replaceAll(",", "");

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
            servingText = servingText.replaceAll(",", "");
            quantityVal = Double.parseDouble(servingText);
        }

        NutritionDescriptor nut = new NutritionDescriptor(nutritionMap, Quantities.getQuantity(quantityVal, unit));
        Food newFood = null;
        try {
            newFood = foodController.editFood(food.getId(), nameField.getText(), FoodGroup.valueOf((String) foodGroupBox.getSelectedItem()), nut);
        } catch (PantsNotFoundException ex) {
            ex.printStackTrace();
        }

        formToNotify.removeAndUpdateFoodSearchBar(food, newFood);
        dispose();
    }
}
