package org.boothverse.foodpants.ui.components;

import lombok.NonNull;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import org.boothverse.foodpants.ui.controllers.FoodController;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.boothverse.foodpants.ui.forms.EditNutritionForm;
import org.boothverse.foodpants.ui.pages.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class NutritionItem extends StandardPanel implements ActionListener {
    protected NutritionInstance nutritionInstance;

    protected int WIDTH = 250;
    protected int HEIGHT = 50;

    protected JButton deleteButton;
    protected JButton editButton;

    Page parent;

    public NutritionItem(@NonNull NutritionInstance nutritionInstance, Component parent) throws PantsNotFoundException {
        super();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.nutritionInstance = nutritionInstance;
        this.parent = (Page) parent;

        initComponents();
    }

    protected void initComponents() throws PantsNotFoundException {
        FoodController foodController = new FoodController();

        String name = foodController.getFood(nutritionInstance.getFoodId()).getName();
        Number amt = nutritionInstance.getQuantity().getValue();

        JPanel panel = new JPanel();
        panel.setBackground(Style.TRANSPARENT);
        add(panel, BorderLayout.EAST);

        // Setup Name of the item
        JLabel nameLabel = new JLabel(name);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        add(nameLabel);

        // Set up quantity text field
        JTextField quantityLabel = new JFormattedTextField(NumberFormat.getIntegerInstance());
        quantityLabel.setEditable(false);
        quantityLabel.setText(amt + "");
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);
        quantityLabel.setPreferredSize(new Dimension(40, 40));

        JLabel unitLabel = new JLabel("(" + nutritionInstance.getQuantity().getUnit().toString() + ")");
        unitLabel.setHorizontalAlignment(JLabel.LEFT);

        panel.add(unitLabel);
        panel.add(quantityLabel);

        // Add edit button
        editButton = new StandardButton("Edit");
        panel.add(editButton);
        editButton.setVisible(false);
        editButton.addActionListener(this);

        // Add delete button
        deleteButton = new StandardButton("Delete");
        panel.add(deleteButton);
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
            EditNutritionForm editNutritionForm = new EditNutritionForm(nutritionInstance, parent);
            editNutritionForm.setVisible(true);

            firePropertyChange("editItem", this, null);
        } else if (e.getSource() == deleteButton) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
                "Are you sure?", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.NO_OPTION) {
                return;
            }

            try {
                NutritionController nutritionController = new NutritionController();
                nutritionController.removeItem(nutritionInstance.getId());
            } catch (PantsNotFoundException pantsNotFoundException) {}

            PageManager.getActivePage().notifyChange("remove", null, null);
            firePropertyChange("deleteItem", this, null);
        }
    }
}
