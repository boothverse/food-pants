package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.components.FoodSearchBar;
import org.boothverse.foodpants.ui.components.QuantitySelector;
import org.boothverse.foodpants.ui.controllers.FoodInstanceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class AddFoodInstanceForm extends StandardForm implements ItemListener, ActionListener {
    FoodSearchBar foodSearchBar;
    QuantitySelector quantityPanel;
    JButton editFoodButton;
    JButton createFoodButton;
    FoodInstanceController controller;

    public AddFoodInstanceForm(FoodInstanceController controller) {
        super("Add Food");

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

        addSubmitButton(e -> {
            if (foodSearchBar.getSelectedItem() != null && !quantityPanel.isEmpty()) {
                controller.addItem(((Food) Objects.requireNonNull(foodSearchBar.getSelectedItem()))
                    .getId(), quantityPanel.getSelectedQuantity());
                PageManager.getActivePage().notifyPage();
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Must select a food and quantity to be added", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
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
            AddFoodForm form = new AddFoodForm();
            form.setFormToBeNotified(this);
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        }
        else if (e.getSource().equals(editFoodButton)) {

        }
    }

    public void updateFoodSearchBar(Food newFood) {
        foodSearchBar.update(newFood);
    }
}
