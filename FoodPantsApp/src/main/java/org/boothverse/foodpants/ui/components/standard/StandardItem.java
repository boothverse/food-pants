package org.boothverse.foodpants.ui.components.standard;

import lombok.Getter;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.forms.EditFoodInstanceForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import systems.uom.unicode.CLDR;
import tech.units.indriya.quantity.Quantities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;


public class StandardItem extends StandardPanel implements ActionListener {

    // Swing components
    protected JLabel nameLabel;
    protected JTextField quantityLabel;
    protected JLabel unitLabel;

    protected JButton deleteButton;
    protected JButton editButton;
    protected Dimension prefSize = new Dimension(300, 50);

    @Getter
    protected FoodInstance foodInstance;

    // TODO: demo only
    private final Food demoFood = new Food("1", "Orange", FoodGroup.FRUIT, null);

    public StandardItem(FoodInstance foodInstance) {
        super();

        // Set swing properties
        setLayout(new BorderLayout());
        setPreferredSize(prefSize);

        // NOTE: Must be Quantity from : tech.units:indriya:2.1.2
        if (foodInstance != null) {
            this.foodInstance = foodInstance;
        }
        else {
            this.foodInstance = demoFood.createInstance(Quantities.getQuantity(10, CLDR.FLUID_OUNCE));
        }

        // Setup fields
        initComponents();
    }

    private void initComponents() {
        // TODO: change so name displays correctly
        Food food = /*Services.FOOD_SERVICE.getFood(foodInstance.getId());*/ demoFood;
        String name = food.getName();
        Number amt = foodInstance.getQuantity().getValue();

        //Add right hand side formatter
        JPanel rightFormat = new JPanel();
        rightFormat.setBackground(Style.TRANSPARENT);
        add(rightFormat, BorderLayout.EAST);

        // Setup Name of the item
        nameLabel = new JLabel(name);
        add(nameLabel);

        // Set up quantity text field
        quantityLabel = new JFormattedTextField(NumberFormat.getIntegerInstance());
        quantityLabel.setEditable(false);
        quantityLabel.setText(amt + "");
        quantityLabel.setHorizontalAlignment(JLabel.RIGHT);
        quantityLabel.setPreferredSize(new Dimension(40, 40));
        rightFormat.add(quantityLabel);


        unitLabel = new JLabel(foodInstance.getQuantity().getUnit().getName());
        unitLabel.setHorizontalAlignment(JLabel.LEFT);
        rightFormat.add(unitLabel);

        // Add edit button
        editButton = new StandardButton("Edit");
        rightFormat.add(editButton);
        editButton.setVisible(false);
        editButton.addActionListener(this);

        // Add delete button
        deleteButton = new StandardButton("Delete");
        rightFormat.add(deleteButton);
        deleteButton.setVisible(false);
        deleteButton.setActionCommand(name);
        deleteButton.addActionListener(this);
    }

    public void setModification(boolean status) {
        editButton.setVisible(status);
        deleteButton.setVisible(status);
        quantityLabel.setEditable(status);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            StandardForm form = new EditFoodInstanceForm(foodInstance);
            form.setLocationRelativeTo(this);
            form.setVisible(true);
        } else if (e.getSource() == deleteButton) {
            firePropertyChange("deleteItem", e.getActionCommand(), null);
        }
    }
}
