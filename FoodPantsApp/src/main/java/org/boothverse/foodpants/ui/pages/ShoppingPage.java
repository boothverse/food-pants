package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.ListItem;

import javax.swing.*;
import java.awt.*;

public class ShoppingPage extends Page {
    private static final String[] labels = {"Edit", "Export", "Mark All"};
    protected JPanel shoppingList;

    public ShoppingPage() {
        super(labels);
        initList();
    }

    private void initList() {
        shoppingList = new JPanel(new GridLayout(10, 1));
        shoppingList.setBackground(Style.PLATINUM);

        // Create a flowlayout wrapper so the component will not be resized
        JPanel listWrapper = new JPanel(new FlowLayout());
        add(listWrapper);
        listWrapper.add(shoppingList);
        listWrapper.setBackground(Style.TRANSPARENT);

        for (int i = 0; i < 10; i++) {
            addListItem("New Item", i);
        }
    }

    public void addListItem(String item, int quantity) {
        shoppingList.add(new ListItem(item, quantity));
    }
}
