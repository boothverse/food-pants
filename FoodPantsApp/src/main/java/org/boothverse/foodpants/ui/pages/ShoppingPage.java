package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.ShoppingItem;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.forms.AddFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;


public class ShoppingPage extends Page {
    private static final String[] labels = {"+", "Modify", "Export", "Mark All", "New"};

    protected JPanel shoppingListDisplay;
    protected StandardButton addItemButton;

    protected ActionListener shoppingListener;
    protected ActionListener addItemListener;

    protected Boolean modifying = false;
    protected List<ShoppingItem> shoppingItems;
    private final int numItems = 10;

    public ShoppingPage() {
        super(labels);
        shoppingItems = new ArrayList<>();
        initList();
        initActionListeners();
    }

    private void initActionListeners() {
        shoppingListener = e -> {
            if (e.getActionCommand().equals("Mark All")) {
                for (ShoppingItem listItem : shoppingItems) {
                    listItem.getCheckBox().setSelected(true);
                }
            }
            else if (e.getActionCommand().equals("Export")) {
//                StandardForm form = new ExportShoppingForm("Export List");
//                form.setLocationRelativeTo(this);
//                form.setVisible(true);
            }
            else if (e.getActionCommand().equals("Modify")) {
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
            }
            else if (e.getActionCommand().equals("New List")) {

            }
            else if (e.getActionCommand().equals("+")) {
                StandardForm form = new AddFoodInstanceForm();
                form.setLocationRelativeTo(this);
                form.setVisible(true);
            }
        };

        sideMenu.buttonMap.forEach((name, button) -> button.addActionListener(shoppingListener));
    }

    private void initList() {
        // Setup display for the items
        shoppingListDisplay = new JPanel();
        shoppingListDisplay.setLayout(new BoxLayout(shoppingListDisplay, BoxLayout.PAGE_AXIS));
        shoppingListDisplay.setBackground(Style.TRANSPARENT);

        // Create a flowlayout wrapper so the component will not be resized
        JPanel listWrapper = new JPanel(new FlowLayout());
        add(listWrapper);
        listWrapper.add(shoppingListDisplay);
        listWrapper.setBackground(Style.TRANSPARENT);
        for (int i = 0; i < numItems; i++) {
            addListItem();
        }
    }

    protected void addListItem() {
        ShoppingItem thisItem = new ShoppingItem(null);
        shoppingItems.add(thisItem);
        shoppingListDisplay.add(thisItem);
    }
}
