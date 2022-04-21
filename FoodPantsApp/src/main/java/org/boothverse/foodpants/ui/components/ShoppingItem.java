package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardCheckbox;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import org.boothverse.foodpants.ui.forms.EditShoppingForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ShoppingItem extends StandardPanel implements ActionListener {
    protected JLabel itemName;
    protected JTextField quantity;
    protected StandardCheckbox checkBox;
    protected ImageIcon icon;
    protected JButton deleteButton;
    protected JButton editButton;

    protected Food food = null;
    protected FoodInstance item = null;


    public ShoppingItem(FoodInstance item) {
        super();
        setLayout(new BorderLayout());
        this.item = item;
        initChildren(item);
        setPreferredSize(new Dimension(400, 60));
    }

    private void initChildren(FoodInstance item) {
        food = Services.FOOD_SERVICE.getFood(item.getId());

        //Add right hand side formatter
        JPanel rightFormat = new JPanel();
        rightFormat.setBackground(Style.TRANSPARENT);
        add(rightFormat, BorderLayout.EAST);

        // Setup Checkbox
        checkBox = new StandardCheckbox();
        add(checkBox, BorderLayout.WEST);

        // Setup Name of the item
        itemName = new JLabel(food.getName());
        //itemName.setFont(Style.bodyStyle);
        add(itemName);

        // Set up quantity text field
        quantity = new JFormattedTextField(NumberFormat.getIntegerInstance());
        quantity.setText(item.getQuantity().getValue() + " " + item.getQuantity().getUnit());
        quantity.setHorizontalAlignment(JLabel.RIGHT);
        quantity.setPreferredSize(new Dimension(40, 40));
        rightFormat.add(quantity);

        // Add edit button
        editButton = new StandardButton("Edit");
        rightFormat.add(editButton);
        editButton.setVisible(false);
        editButton.addActionListener(this);

        // Add delete button
        deleteButton = new StandardButton("Delete");
        rightFormat.add(deleteButton);
        deleteButton.setVisible(false);
        deleteButton.setActionCommand(food.getName());
        deleteButton.addActionListener(this);
    }

    public void setModification(boolean status) {
        editButton.setVisible(status);
        deleteButton.setVisible(status);
    }

    public StandardCheckbox getCheckBox() {
        return checkBox;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            StandardForm form = new EditShoppingForm("Edit Item");
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        } else if (e.getSource() == deleteButton) {
            firePropertyChange("deleteItem", e.getActionCommand(), null);
        }
    }
}
