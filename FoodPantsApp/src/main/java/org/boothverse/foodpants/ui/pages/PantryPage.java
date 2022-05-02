package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.PantryItem;
import org.boothverse.foodpants.ui.components.ShoppingItem;
import org.boothverse.foodpants.ui.components.standard.ItemList;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
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

    private static boolean modifyingPantry;
    private final PantryController pantryController = new PantryController();
    private final ItemList itemDisplay;

    public PantryPage() {
        super(labels);
        modifyingPantry = false;

        itemDisplay = new ItemList(2, this);
        add(itemDisplay);

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
                for (StandardItem pantryPanel : itemDisplay.getItems()) {
                    pantryPanel.setModification(modifyingPantry);
                }
            }
        }
    }

    protected void updateList() {
        List<FoodInstance> listItems = pantryController.getItems();

        itemDisplay.removeAll();
        for (FoodInstance food : listItems) {
            itemDisplay.add(new PantryItem(food));
        }
        revalidate();
    }

    @Override
    public void notifyPage() {
        updateList();
    }
}
