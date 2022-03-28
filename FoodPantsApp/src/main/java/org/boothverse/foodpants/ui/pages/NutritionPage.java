package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NutritionPage extends Page {
    private static final String[] labels = {"+", "Goals", "Report"};
    private static final String[] timeViews = {"Day", "Week", "Month"};
    private static JPanel timeContainer;

    private static final ActionListener holdColor = e -> {
            // Handle button color change
            for (Component c : timeContainer.getComponents()) { c.setBackground(Style.GREY_3); }
            ((StandardButton) e.getSource()).setBackground(((StandardButton) e.getSource()).getBackground().darker());
    };

    public NutritionPage() {
        super(labels);
        initTimeView();
    }

    private void initTimeView() {
        timeContainer = new JPanel(new GridLayout(1,timeViews.length + 1));
        JPanel timeWrapper = new JPanel(new FlowLayout());
        timeWrapper.setBackground(Style.TRANSPARENT);
        timeWrapper.add(timeContainer, FlowLayout.LEFT);

        for (String label: timeViews) {
            StandardButton timeButton = new StandardButton(label);
            timeButton.setBackground(Style.GREY_3);
            timeButton.addActionListener(holdColor);
            timeButton.setPreferredSize(new Dimension(150, 30));
            timeContainer.add(timeButton);
        }
        add(timeWrapper, BorderLayout.NORTH);
    }
}
