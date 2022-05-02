package org.boothverse.foodpants.ui.components.standard;

import lombok.Getter;
import lombok.NonNull;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.controllers.FoodController;

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
    protected Dimension prefSize = new Dimension(400, 50);

    @Getter
    protected FoodInstance foodInstance;
    protected FoodController foodController = new FoodController();

    public StandardItem(@NonNull FoodInstance foodInstance) {
        super();

        // Set swing properties
        setLayout(new BorderLayout());
        setPreferredSize(prefSize);

        this.foodInstance = foodInstance;

        // Setup fields
        initComponents();
    }

    private void initComponents() {
        Food food = foodController.getFood(foodInstance.getId());
        String name = food.getName();
        Number amt = foodInstance.getQuantity().getValue();

        //Add right hand side formatter
        JPanel rightFormat = new JPanel();
        rightFormat.setBackground(Style.TRANSPARENT);
        add(rightFormat, BorderLayout.EAST);

        // Setup Name of the item
        nameLabel = new JLabel(name);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        add(nameLabel);

        // Set up quantity text field
        quantityLabel = new JFormattedTextField(NumberFormat.getIntegerInstance());
        quantityLabel.setEditable(false);
        quantityLabel.setText(amt + "");
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);
        quantityLabel.setPreferredSize(new Dimension(40, 40));

        unitLabel = new JLabel("(" + foodInstance.getQuantity().getUnit().toString() + ")");
        unitLabel.setHorizontalAlignment(JLabel.LEFT);
        rightFormat.add(unitLabel);
        rightFormat.add(quantityLabel);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
//            StandardForm form = new EditFoodInstanceForm(foodInstance);
//            form.setLocationRelativeTo(this);
//            form.setVisible(true);
        } else if (e.getSource() == deleteButton) {
            firePropertyChange("deleteItem", e.getActionCommand(), null);
        }
    }
}
