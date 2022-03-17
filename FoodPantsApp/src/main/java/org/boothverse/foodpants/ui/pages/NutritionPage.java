package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.StandardButton;

import javax.swing.*;
import java.awt.*;

public class NutritionPage extends Page {
    private static final String[] labels = {"+", "Goals", "Report"};
    private static final String[] timeViews = {"Day", "Week", "Month"};

    public NutritionPage() {
        super(labels);
        initTimeView();
    }

    private void initTimeView() {
        JPanel timeContainer = new JPanel(new GridLayout(1,timeViews.length + 1));
        JPanel timeWrapper = new JPanel(new FlowLayout());
        timeWrapper.setBackground(Style.TRANSPARENT);
        timeWrapper.add(timeContainer, FlowLayout.LEFT);

        for (String label: timeViews) {
            StandardButton timeButton = new StandardButton(label);
            timeButton.setBackground(Style.GREY_3);
            timeButton.setPreferredSize(new Dimension(150, 30));
            timeContainer.add(timeButton);
        }
        buttonPane.add(timeWrapper, BorderLayout.NORTH);
    }
}
