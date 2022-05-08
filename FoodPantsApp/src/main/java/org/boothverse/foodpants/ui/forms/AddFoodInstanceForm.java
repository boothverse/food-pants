package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.business.services.exceptions.PantsConversionFailedException;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.components.FoodSearchBar;
import org.boothverse.foodpants.ui.components.IngredientItem;
import org.boothverse.foodpants.ui.components.QuantitySelector;
import org.boothverse.foodpants.ui.components.standard.Searchable;
import org.boothverse.foodpants.ui.controllers.FoodInstanceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class AddFoodInstanceForm extends StandardForm implements ItemListener, ActionListener, Searchable {
    FoodSearchBar foodSearchBar;
    QuantitySelector quantityPanel;
    JButton editFoodButton;
    JButton createFoodButton;
    FoodInstanceController controller;

    public AddFoodInstanceForm(String header, FoodInstanceController controller, Component parent) {
        super(header, parent);

        this.controller = controller;
        initSwing();
        initForm();
    }

    private void initSwing() {
        editFoodButton = new JButton("Edit Selected");
        editFoodButton.setEnabled(false);
        createFoodButton = new JButton("Create New");
        foodSearchBar = new FoodSearchBar();
        foodSearchBar.addItemListener(this);

        quantityPanel = new QuantitySelector();

        // Use set preferred size if you want GridBagLayout to actually listen to what you want and not
        // ignore you like the garbage swing layout manager that it is.
        quantityPanel.getQuantityValueField().setPreferredSize(
            new Dimension(getWidth() / 2, quantityPanel.getQuantityValueField().getHeight()));

        editFoodButton.addActionListener(this);
        createFoodButton.addActionListener(this);
    }

    @Override
    void initForm() {
        int i = 0;
        addLeftComponent(new JLabel("Food"), ++i);
        addRightComponent(foodSearchBar, i);

        addRightComponent(editFoodButton, ++i);
        addRightComponent(createFoodButton, ++i);
        addRightComponent(new JPanel(),++i);

        addLeftComponent(new JLabel("Quantity"), ++i);
        addRightComponent(quantityPanel, i);
        addRightComponent(new JPanel(),++i);

        addSubmitButton(e -> submitForm());
    }

    protected void submitForm() {
        if (foodSearchBar.getSelectedItem() != null && !quantityPanel.isEmpty()) {
            FoodInstance newFood;
            if (controller != null) {
                try {
                    newFood = controller.addItem(((Food) Objects.requireNonNull(foodSearchBar.getSelectedItem()))
                        .getId(), quantityPanel.getSelectedQuantity());
                    PageManager.getActivePage().notifyChange("add", null, newFood);
                } catch (PantsConversionFailedException e) {
                    JOptionPane.showMessageDialog(this, "ERROR: failed to add food instance due to incompatible units");
                }
            }
            else {
                newFood = ((Food) Objects.requireNonNull(foodSearchBar.getSelectedItem())).createInstance(quantityPanel.getSelectedQuantity());
                if (AddRecipeForm.class.isAssignableFrom(parent.getClass())) {
                    ((AddRecipeForm)parent).ingredients.add(new IngredientItem(newFood));
                    parent.revalidate();
                    parent.repaint();
                }
            }

            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Must select a food and quantity to be added", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            editFoodButton.setEnabled(false);
        }
        if (e.getStateChange() == ItemEvent.SELECTED) {
            editFoodButton.setEnabled(true);
            Object item = e.getItem();
            System.out.println(item.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(createFoodButton)) {
            AddFoodForm form = new AddFoodForm(this);
            form.setLocationRelativeTo(parent);
            form.setFormToBeNotified(this);
            form.setVisible(true);
        }
        else if (e.getSource().equals(editFoodButton)) {
            Food food = (Food) foodSearchBar.getModel().getSelectedItem();
            EditFoodForm form = new EditFoodForm(food, this);
            form.setFormToBeNotified(this);
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
}
