package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.FoodSearchBar;
import org.boothverse.foodpants.ui.components.ItemList;
import org.boothverse.foodpants.ui.components.standard.*;
import org.boothverse.foodpants.ui.controllers.RecipeController;
import org.boothverse.foodpants.ui.pages.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Objects;

public class AddRecipeForm extends StandardForm implements ActionListener, Notifiable, Searchable {
    FoodSearchBar foodSearchBar;
    JFormattedTextField servingsPanel;
    JButton createFoodButton;
    TextArea instructionArea;
    ItemList ingredients;
    JButton addIngredientButton;

    protected final int WIDTH = 640;
    protected final int HEIGHT = 480;

    protected RecipeController recipeController;

    public AddRecipeForm(String header, Component parent) {
        super(header, parent);
        setSize(WIDTH, HEIGHT);

        wrapperPanel.remove(contentPanel);
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

        JScrollPane viewer = new JScrollPane(contentPanel);
        viewer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        wrapperPanel.add(viewer);

        recipeController = new RecipeController();
        initSwing();
        initForm();
    }

    private void initSwing() {
        foodSearchBar = new FoodSearchBar();

        createFoodButton = new JButton("Add New Recipe");
        createFoodButton.addActionListener(this);
        addIngredientButton = new JButton("Add Ingredient");
        addIngredientButton.addActionListener(this);

        servingsPanel = new JFormattedTextField(NumberFormat.getNumberInstance());
        instructionArea = new TextArea();

        assert(parent.getClass().equals(Page.class));
        ingredients = new ItemList(1, this);
        ingredients.setBackground(Style.TRANSPARENT);
    }

    @Override
    void initForm() {
        int i = 0;

        addLeftComponent(new JLabel("Recipe Name"), ++i);
        addRightComponent(foodSearchBar, i);

        addRightComponent(createFoodButton, ++i);
        addRightComponent(new JPanel(),++i);

        addLeftComponent(new JLabel("Servings"), ++i);
        addRightComponent(servingsPanel, i);

        addLeftComponent(new JLabel("Ingredients"), ++i);
        addRightComponent(addIngredientButton, i);
        addRightComponent(ingredients, ++i);

        addLeftComponent(new JLabel("Instructions"), ++i);
        addRightComponent(instructionArea, ++i);

        addRightComponent(new JPanel(),++i);

        addSubmitButton(e -> {
            if (foodSearchBar.getSelectedItem() != null && !servingsPanel.getText().isEmpty()) {
                Recipe newRecipe = recipeController.addRecipe(
                    (Food)foodSearchBar.getSelectedItem(),
                    ingredients.getItems().stream().map(StandardItem::getFoodInstance).toList(),
                    instructionArea.getText(), Double.parseDouble(servingsPanel.getText()));

                PageManager.getActivePage().notifyChange("add recipe", null, newRecipe);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Must select a recipe and number of servings.",
                    "Error: Empty Fields", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(createFoodButton)) {
            AddFoodForm form = new AddFoodForm(this);
            form.setLocationRelativeTo(parent);
            form.setFormToBeNotified(this);
            form.setVisible(true);
        }
        if (e.getSource().equals(addIngredientButton)) {
            AddFoodInstanceForm form = new AddFoodInstanceForm("Add Ingredient", null, this);
            form.setLocationRelativeTo(parent);
            form.setVisible(true);
        }
    }

    public void updateFoodSearchBar(Food newFood) {
        foodSearchBar.update(newFood);
    }

    public void removeAndUpdateFoodSearchBar(Food oldFood, Food newFood) {
        foodSearchBar.removeAndUpdate(oldFood, newFood);
    }

    @Override
    public void notifyChange(String message, Object oldValue, Object newValue) {
        if (Objects.equals(message, "add")) {

        }
    }
}
