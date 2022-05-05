package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.PantryItem;
import org.boothverse.foodpants.ui.components.ItemList;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.controllers.PantryController;
import org.boothverse.foodpants.ui.forms.AddFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.EditFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.SearchForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class PantryPage extends Page {
    private static final String[] labels = {"+", "Search", "Modify"};

    private static boolean modifyingPantry;
    private static boolean searchingPantry;
    private final PantryController pantryController = new PantryController();
    private final ItemList itemDisplay;

    public PantryPage() {
        super(labels);
        modifyingPantry = false;
        searchingPantry = false;

        itemDisplay = new ItemList(2, this);
        add(itemDisplay);

        updateList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+" -> {
                StandardForm form = new AddFoodInstanceForm("Add Food",pantryController,this);
                form.setVisible(true);
            }
            case "Search" -> {
                JButton searchBtn = (JButton) e.getSource();
                searchingPantry = !searchingPantry;
                if (searchingPantry) {
                    StandardForm form = new SearchForm(this);
                    form.setVisible(true);
                    searchBtn.setBackground(Style.GREY_0);
                } else {
                    searchBtn.setBackground(Style.GREY_1);
                    updateList();
                }
            }
            case "Modify" -> {
                JButton modifyBtn = (JButton) e.getSource();
                modifyingPantry = !modifyingPantry;
                if (modifyingPantry) {
                    modifyBtn.setBackground(Style.GREY_0);
                } else {
                    modifyBtn.setBackground(Style.GREY_1);
                }
                itemDisplay.setModifiable(modifyingPantry);
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

    protected void updateList(List<FoodInstance> listItems) {
        itemDisplay.removeAll();
        for (FoodInstance food : listItems) {
            itemDisplay.add(new PantryItem(food));
        }
        revalidate();
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        if (Objects.equals(message, "add")) {
            updateList();
        }
        else if (Objects.equals(message, "remove")) {
            try {
                pantryController.removeItem(((StandardItem)oldValue).getFoodInstance().getId());
            }
            catch (PantsNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (Objects.equals(message, "edit")) {
            StandardForm editItemForm = new EditFoodInstanceForm(((StandardItem) oldValue).getFoodInstance(), pantryController, this);
            editItemForm.setVisible(true);
        }
        else if (Objects.equals(message, "search")) {
            updateList(pantryController.searchByFoodName((String)newValue));
        }
        else if (Objects.equals(message, "update")) {
            updateList();
        }
    }
}
