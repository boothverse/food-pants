package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.ui.controllers.FoodController;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class FoodSearchBar extends JComboBox<String> {
    protected DefaultComboBoxModel<String> model;
    private FoodController foodController = new FoodController();
    private List<Food> foods;
    private List<String> foodNames;

    public FoodSearchBar() {
        super();
        model = new DefaultComboBoxModel<>();
        setModel(model);
        AutoCompleteDecorator.decorate(this);


    }

    public void populate() {
        model.removeAllElements();

        foods = foodController.getFoods();
        foodNames = foods.stream().map(Food::getName).sorted(Comparator.naturalOrder()).toList();

        model.addAll(foodNames);
        revalidate();
    }
}
