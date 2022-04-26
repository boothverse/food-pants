package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.ShoppingItem;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.forms.AddShoppingForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class ShoppingPage extends Page {
    private static final String[] labels = {"Modify", "Export", "Mark All", "New List"};

    protected JPanel shoppingListDisplay;
    protected StandardButton addItemButton;

    protected ActionListener shoppingListener;
    protected ActionListener addItemListener;

    protected Boolean modifying = false;

    protected List<ShoppingItem> shoppingItems;

    public ShoppingPage() {
        super(labels);
        shoppingItems = new ArrayList<>();
        initList();
        updateDisplay();
        initActionListeners();
    }

    private void initActionListeners() {
        shoppingListener = e -> {
            if (e.getActionCommand().equals("Mark All")) {
                for (ShoppingItem listItem : shoppingItems) {
                    listItem.getCheckBox().setSelected(true);
                }
            } else if (e.getActionCommand().equals("Export")) {

            } else if (e.getActionCommand().equals("Modify")) {
                JButton modifyBtn = (JButton) e.getSource();
                modifying = !modifying;

                if (modifying) {
                    modifyBtn.setBackground(Style.GREY_0);
                } else {
                    modifyBtn.setBackground(Style.GREY_1);
                }

                for (StandardItem listItem : shoppingItems) {
                    listItem.setModification(modifying);
                }
            } else if (e.getActionCommand().equals("New List")) {

            }
        };

        sideMenu.buttonMap.forEach((name, button) -> button.addActionListener(shoppingListener));
    }

    private void initList() {
        // Setup display for the items
        shoppingListDisplay = new JPanel(new GridLayout(10 + 1, 2, 10, 0));
        shoppingListDisplay.setBackground(Style.TRANSPARENT);

        // Create a flowlayout wrapper so the component will not be resized
        JPanel listWrapper = new JPanel(new FlowLayout());
        add(listWrapper);
        listWrapper.add(shoppingListDisplay);
        listWrapper.setBackground(Style.TRANSPARENT);
        for (int i = 0; i < 20; i++) {
            addListItem();
        }

        // Add the add item button
        JPanel addItemWrapper = new JPanel(new BorderLayout());
        addItemWrapper.setBackground(Style.TRANSPARENT);
        shoppingListDisplay.add(addItemWrapper);
        addItemButton = new StandardButton("Add Item");
        addItemListener = e -> {
            StandardForm form = new AddShoppingForm("Add Item");
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        };
        addItemButton.addActionListener(addItemListener);
        addItemWrapper.add(addItemButton, BorderLayout.LINE_END);
    }

    protected void addListItem() {
        ShoppingItem thisItem = new ShoppingItem(null);
        shoppingItems.add(thisItem);
    }

    protected void removeShoppingItem(String name) {
        shoppingItems.removeIf(listItem -> Objects.equals(listItem.getName(), name));
        updateDisplay();
    }

    protected void updateDisplay() {
        shoppingListDisplay.removeAll();
        for (StandardItem listItem : shoppingItems) {
            shoppingListDisplay.add(listItem);
        }
    }
}
