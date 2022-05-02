package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.FoodSearchBar;

import javax.swing.*;
import java.awt.*;

public class SearchForm extends StandardForm {
    protected FoodSearchBar searchBar;

    public SearchForm() {
        super("Search");
        searchBar = new FoodSearchBar();
        setSize(300, 200);
        initForm();
    }

    @Override
    void initForm() {
        int i = 0;
        searchBar.setPreferredSize(new Dimension(this.getWidth() - 50, 25));
        addMiddleComponent(searchBar, i);

        JPanel spacer = new JPanel();
        spacer.setBackground(Style.TRANSPARENT);
        spacer.setPreferredSize(new Dimension(this.getWidth() - 50, 20));

        addRightComponent(spacer, ++i);

        addSubmitButton(e -> dispose());
    }
}
