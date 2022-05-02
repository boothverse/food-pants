package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.PantryItem;
import org.boothverse.foodpants.ui.components.RecipeItem;
import org.boothverse.foodpants.ui.components.standard.ItemList;
import org.boothverse.foodpants.ui.controllers.FoodInstanceController;
import org.boothverse.foodpants.ui.controllers.RecipeController;
import org.boothverse.foodpants.ui.forms.RecipeForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RecipePage extends Page {
    private static final String[] labels = {"+", "Recommend", "Nutrition", "Search"};

    protected ItemList items;
    protected RecipeController recipeController = new RecipeController();

    // TODO: add grandma cerny's synatactic sugar cookie recipe
    public RecipePage() {
        super(labels);

        items = new ItemList(1, this);
        items.getListDisplay().setLayout(new GridLayout(0, 1, 0 ,10));
        add(items);
        updateList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                RecipeForm form = new RecipeForm("Create Recipe");
                form.setLocationRelativeTo(this);
                form.setVisible(true);
                break;
            case "Recommend":
            case "Nutrition":
            case "Search":
                break;
        }
    }

    protected void updateList() {
        List<Recipe> listItems = recipeController.getRecipes();

        items.removeAll();
        for (Recipe item : listItems) {
            items.add(new RecipeItem(item));
        }
        revalidate();
    }

    @Override
    public void notifyPage() {
        updateList();
    }
}