package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.persistence.Food;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FoodSearchBar extends JComboBox<String> {
    protected DefaultComboBoxModel<String> model;

    public FoodSearchBar() {
        super();
        model = new DefaultComboBoxModel<>();
        setModel(model);
        AutoCompleteDecorator.decorate(this);
    }

    public FoodSearchBar(Collection<? extends Food> foods) {
        this();

        List<String> foodNames = new ArrayList<>();
        foods.forEach(food -> foodNames.add(food.getName()));
        populate(foodNames);
    }

    // TODO: for testing purposes only really
    public FoodSearchBar(String[] foods) {
        this();

        populate(List.of(foods));
    }

    public void populate(Collection<? extends String> c) {
        model.removeAllElements();
        model.addAll(c);
        model.addElement("Create New Food");
        revalidate();
    }
}
