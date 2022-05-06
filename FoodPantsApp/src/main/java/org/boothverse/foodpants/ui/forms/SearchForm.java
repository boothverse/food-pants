package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.FoodSearchBar;
import org.boothverse.foodpants.ui.components.standard.Notifiable;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SearchForm extends StandardForm {
    protected FoodSearchBar searchBar;
    private Notifiable toNotifySearch;

    public SearchForm(Notifiable parent) {
        super("Search", (Component) parent);
        searchBar = new FoodSearchBar();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        toNotifySearch = parent;
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

        addSubmitButton(e -> {
            if (searchBar.getSelectedItem() == null) {
                toNotifySearch.notifyChange("update", null, null);
            }
            else {
                toNotifySearch.notifyChange("search", null, Objects.requireNonNull(((Food)searchBar.getSelectedItem()).getName()));
            }
            dispose();
        });
    }
}
