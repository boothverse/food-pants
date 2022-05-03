package org.boothverse.foodpants.ui.components;


import org.boothverse.foodpants.business.services.util.UnitToString;
import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardLabel;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;


import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class GoalItem extends StandardPanel {
    protected JLabel nameLabel;
    protected JTextField quantityLabel;

    protected int WIDTH = 250;
    protected int HEIGHT = 50;

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

        JLabel unitLabel = new JLabel("(" + UnitToString.convertUnitToString(goal.getDailyQuantity().getUnit()) + ")");
        unitLabel.setHorizontalAlignment(JLabel.LEFT);

        JLabel goalTypeLabel = new JLabel(goalType);
        goalTypeLabel.setHorizontalAlignment(JLabel.LEFT);

        panel.add(goalTypeLabel);
        panel.add(unitLabel);
        panel.add(quantityLabel);
    }
}
