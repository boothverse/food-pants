package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.business.services.util.UnitToString;
import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.boothverse.foodpants.ui.forms.EditGoalForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class GoalItem extends StandardPanel implements ActionListener {
    protected JLabel nameLabel, goalTypeLabel, unitLabel;
    protected JTextField quantityLabel;

    protected int WIDTH = 250;
    protected int HEIGHT = 50;

    protected JButton deleteButton;
    protected JButton editButton;

    Goal goal;

    public GoalItem(Goal g) {
        super();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        goal = g;
        initChildren();
    }

    private void initChildren() {
        String name = goal.getNutritionType().toString();
        Number amt = goal.getDailyQuantity().getValue();

        String goalType = (goal.getGoalType() == GoalType.MAXIMIZE) ? "Max" : "Min";

        JPanel panel = new JPanel();
        panel.setBackground(Style.TRANSPARENT);
        add(panel, BorderLayout.EAST);

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

        unitLabel = new JLabel("(" + UnitToString.convertUnitToString(goal.getDailyQuantity().getUnit()) + ")");
        unitLabel.setHorizontalAlignment(JLabel.LEFT);

        goalTypeLabel = new JLabel(goalType);
        goalTypeLabel.setHorizontalAlignment(JLabel.LEFT);

        panel.add(goalTypeLabel);
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

        goalTypeLabel.setVisible(!status);
        unitLabel.setVisible(!status);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            EditGoalForm editGoalForm = new EditGoalForm(goal, new NutritionController(), this);
            editGoalForm.setVisible(true);

            firePropertyChange("editItem", this, null);
        } else if (e.getSource() == deleteButton) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
                "Are you sure?", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.NO_OPTION) {
                return;
            }

            try {
                NutritionController nutritionController = new NutritionController();
                nutritionController.removeGoal(goal.getId());
            } catch (PantsNotFoundException pantsNotFoundException) {}

            PageManager.getActivePage().notifyChange("remove", null, null);
            firePropertyChange("deleteItem", this, null);
        }
    }
}
