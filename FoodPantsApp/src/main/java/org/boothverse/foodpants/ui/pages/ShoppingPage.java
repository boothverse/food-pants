package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.ShoppingItem;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.forms.AddShoppingForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ShoppingPage extends Page {
    private static final String[] labels = {"Modify", "Export", "Mark All", "New List"};
    private static final String[] DEMO_ITEMS = {"Apple", "Banana", "Ribeye", "Oreos", "Bread Flour", "Vanilla Extract", "Eggs"};
    private static final int[] DEMO_AMTS = {6, 4, 2, 1, 1, 1, 12};
    protected JPanel shoppingList;
    protected StandardButton addItemButton;

    protected ActionListener shoppingListener;
    protected ActionListener addItemListener;
    protected Boolean modifying = false;

    public ShoppingPage() {
        super(labels);
        initList();
        initActionListeners();
    }

    private void initActionListeners() {
        shoppingListener = e -> {
            if (e.getActionCommand().equals("Mark All")) {
                for (ShoppingItem listItem : getShoppingItems()) {
                    listItem.getCheckBox().setSelected(true);
                }
            }
            else if (e.getActionCommand().equals("Export")) {

            }
            else if (e.getActionCommand().equals("Modify")) {
                JButton modifyBtn = (JButton) e.getSource();
                modifying = !modifying;

                if (modifying) {
                    modifyBtn.setBackground(Style.GREY_0);
                } else {
                    modifyBtn.setBackground(Style.GREY_1);
                }

                for (ShoppingItem listItem : getShoppingItems()) {
                        listItem.setModification(modifying);
                }
            }
            else if (e.getActionCommand().equals("New List")) {

            }
        };

        sideMenu.buttonMap.forEach((name, button) -> {
            button.addActionListener(shoppingListener);
        });
    }

    private void initList() {
        shoppingList = new JPanel(new GridLayout(DEMO_ITEMS.length + 1, 1));
        shoppingList.setBackground(Style.TRANSPARENT);

        // Create a flowlayout wrapper so the component will not be resized
        JPanel listWrapper = new JPanel(new FlowLayout());
        add(listWrapper);
        listWrapper.add(shoppingList);
        listWrapper.setBackground(Style.TRANSPARENT);

        for (int i = 0; i < DEMO_ITEMS.length; i++) {
            addListItem(DEMO_ITEMS[i], DEMO_AMTS[i]);
        }

        // Add the add item button
        JPanel addItemWrapper = new JPanel(new BorderLayout());
        addItemWrapper.setBackground(Style.TRANSPARENT);
        shoppingList.add(addItemWrapper);
        addItemButton = new StandardButton("Add Item");
        addItemListener = e -> {
            StandardForm form = new AddShoppingForm("Add Item");
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        };
        addItemButton.addActionListener(addItemListener);
        addItemWrapper.add(addItemButton, BorderLayout.LINE_END);
    }

    protected void addListItem(String item, int quantity) {
        shoppingList.add(new ShoppingItem(item, quantity));
    }

    protected ShoppingItem[] getShoppingItems() {
        ShoppingItem[] returnPanels = new ShoppingItem[DEMO_ITEMS.length];
        int i = 0;
        for (Component listItem : shoppingList.getComponents()) {
            if (listItem.getClass() == ShoppingItem.class) {
                returnPanels[i++] = (ShoppingItem) listItem;
            }
        }
        return returnPanels;
    }
}
