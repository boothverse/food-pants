package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.ShoppingItem;
import org.boothverse.foodpants.ui.components.SideMenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ShoppingPage extends Page {
    private static final String[] labels = {"Edit", "Export", "Mark All"};
    private static final String[] DEMO_ITEMS = {"Apple", "Banana", "Ribeye", "Oreos", "Bread Flour", "Vanilla Extract", "Eggs"};
    private static final int[] DEMO_AMTS = {6, 4, 2, 1, 1, 1, 12};
    protected JPanel shoppingList;

    protected ActionListener shoppingListener;

    public ShoppingPage() {
        super(labels);
        initList();

        initActionListener();

    }

    private void initActionListener() {
        shoppingListener = e -> {
            System.out.println("Shopping: " + e.getActionCommand());
            if (e.getActionCommand().equals("Mark All")) {
                for (Component listItem : shoppingList.getComponents()) {
                    ((ShoppingItem)listItem).getCheckBox().setSelected(true);
                }
            }
            else if (e.getActionCommand().equals("Export")) {

            }
            else if (e.getActionCommand().equals("Edit")) {

            }
        };
        sideMenu.buttonMap.forEach((name, button) -> {
            button.addActionListener(shoppingListener);
        });
    }

    private void initList() {
        shoppingList = new JPanel(new GridLayout(10, 1));
        shoppingList.setBackground(Style.PLATINUM);

        // Create a flowlayout wrapper so the component will not be resized
        JPanel listWrapper = new JPanel(new FlowLayout());
        add(listWrapper);
        listWrapper.add(shoppingList);
        listWrapper.setBackground(Style.TRANSPARENT);

        // TODO: These are placeholders, also need to left align
        for (int i = 0; i < DEMO_ITEMS.length; i++) {
            addListItem(DEMO_ITEMS[i], DEMO_AMTS[i]);
        }
    }

    public void addListItem(String item, int quantity) {
        shoppingList.add(new ShoppingItem(item, quantity));
    }
}
