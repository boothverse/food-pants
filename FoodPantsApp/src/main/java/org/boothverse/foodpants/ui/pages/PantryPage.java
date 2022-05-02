package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.PantryItem;
import org.boothverse.foodpants.ui.components.ShoppingItem;
import org.boothverse.foodpants.ui.controllers.PantryController;
import org.boothverse.foodpants.ui.forms.AddFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.SearchForm;
import org.boothverse.foodpants.ui.forms.StandardItemForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class PantryPage extends Page {
    private static final String[] labels = {"+", "Search", "Modify"};

    private static List<PantryItem> pantryItems;
    private static JPanel displayPanel;
    private static boolean modifyingPantry;
    private final PantryController pantryController = new PantryController();

    public PantryPage() {
        super(labels);
        pantryItems = new ArrayList<>();
        modifyingPantry = false;

        JPanel listWrapper = new JPanel(new FlowLayout());
        listWrapper.setBackground(Style.TRANSPARENT);

        displayPanel = new JPanel(new GridLayout(0, 2));
        displayPanel.setBackground(Style.TRANSPARENT);

        listWrapper.add(displayPanel);
        add(listWrapper);

        updateList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+" -> {
                StandardForm form = new AddFoodInstanceForm(pantryController);
                form.setLocationRelativeTo(this);
                form.setVisible(true);
            }
            case "Search" -> {
                StandardForm form = new SearchForm();
                form.setLocationRelativeTo(this);
                form.setVisible(true);
                this.setFocusable(false);
            }
            case "Modify" -> {
                JButton modifyBtn = (JButton) e.getSource();
                modifyingPantry = !modifyingPantry;
                if (modifyingPantry) {
                    modifyBtn.setBackground(Style.GREY_0);
                } else {
                    modifyBtn.setBackground(Style.GREY_1);
                }
                for (PantryItem pantryPanel : pantryItems) {
                    pantryPanel.setModification(modifyingPantry);
                }
            }
        }
    }

    protected void updateList() {
        List<FoodInstance> listItems = pantryController.getItems();

        pantryItems.clear();
        displayPanel.removeAll();
        for (FoodInstance item : listItems) {
            PantryItem thisItem = new PantryItem(item);
            pantryItems.add(thisItem);
            displayPanel.add(thisItem);
        }
        revalidate();
    }

    @Override
    public void notifyPage() {
        updateList();
    }
}
