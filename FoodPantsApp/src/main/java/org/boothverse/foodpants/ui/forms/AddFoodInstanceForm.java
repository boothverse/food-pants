package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.components.FoodSearchBar;
import org.boothverse.foodpants.ui.components.QuantitySelector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddFoodInstanceForm extends StandardForm implements ItemListener, ActionListener {
    FoodSearchBar foodSearchBar;
    QuantitySelector quantityPanel;
    JButton foodButton;
    JButton createFoodButton;

    public AddFoodInstanceForm() {
        super("Add Food");

        initSwing();
        initForm();
    }

    private void initSwing() {
        foodButton = new JButton("Edit Selected");
        foodButton.setEnabled(false);
        createFoodButton = new JButton("Create New");
        foodSearchBar = new FoodSearchBar();
        foodSearchBar.addItemListener(this);
        foodSearchBar.populate(java.util.List.of(new String[]{"Apple", "Orange", "Banana"}));

        quantityPanel = new QuantitySelector();
        quantityPanel.getQuantityValueField().setPreferredSize(
            new Dimension(getWidth() / 2, quantityPanel.getQuantityValueField().getHeight()));

        foodButton.addActionListener(this);
        createFoodButton.addActionListener(this);
    }

    @Override
    void initForm() {
        int i = 0;
        addLeftComponent(new JLabel("Food"), ++i);
        addRightComponent(foodSearchBar, i);


        addRightComponent(foodButton, ++i);
        addRightComponent(createFoodButton, ++i);
        addRightComponent(new JPanel(),++i);

        addLeftComponent(new JLabel("Quantity"), ++i);
        addRightComponent(quantityPanel, i);
        addRightComponent(new JPanel(),++i);

        addSubmitButton(e -> dispose());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            foodButton.setEnabled(false);
        }
        if (e.getStateChange() == ItemEvent.SELECTED) {
            foodButton.setEnabled(true);
            Object item = e.getItem();
            System.out.println(item.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(createFoodButton)) {
            AddFoodForm form = new AddFoodForm();
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        }
        else {

        }
    }
}
