package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.components.FoodSearchBar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchForm extends StandardForm {
    protected FoodSearchBar searchBar;

    public SearchForm() {
        super("Search");
        searchBar = new FoodSearchBar();
        setSize(300, 200);
        initForm();
    }

    SearchForm(List<Food> foods) {
        this();
    }

    @Override
    void initForm() {
        int i = 0;
        searchBar.setPreferredSize(new Dimension(this.getWidth() - 50, 30));
        addMiddleComponent(searchBar, i);
    }
}
