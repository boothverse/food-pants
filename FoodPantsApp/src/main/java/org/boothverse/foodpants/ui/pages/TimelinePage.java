package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.persistence.IdObject;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.TimelineDropdown;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.controllers.NutritionController;
import org.boothverse.foodpants.ui.forms.AddNutritionForm;
import org.boothverse.foodpants.ui.forms.StandardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimelinePage extends NutritionPage {
    private static final String[] timeViews = {"Day", "Week", "Month"};

    protected static JPanel timeContainer;
    private JPanel currTimeline;

    protected static final ActionListener holdColor = e -> {
        // Handle button color change
        for (Component c : timeContainer.getComponents()) {
            c.setBackground(Style.GREY_3);
        }
        ((StandardButton) e.getSource()).setBackground(((StandardButton) e.getSource()).getBackground().darker());
    };

    public TimelinePage() {
        initTimeline();
    }

    private void initTimeline() {
        timeContainer = new JPanel(new GridLayout(1, timeViews.length + 1));
        JPanel timeWrapper = new JPanel(new FlowLayout());
        timeWrapper.setBackground(Style.TRANSPARENT);
        timeWrapper.add(timeContainer, FlowLayout.LEFT);

        currTimeline = new JPanel(new FlowLayout());

        for (String label : timeViews) {
            StandardButton timeButton = new StandardButton(label);
            timeButton.setBackground(Style.GREY_3);
            timeButton.addActionListener(holdColor);
            timeButton.setPreferredSize(new Dimension(150, 30));
            timeButton.addActionListener((e) -> setTimeLineView(timeButton.getText()));

            timeContainer.add(timeButton);
        }

        add(timeWrapper, BorderLayout.NORTH);
        add(currTimeline);
    }

    private void setTimeLineView(String viewType) {
        if (currTimeline != null) { currTimeline.removeAll(); }

        Calendar c = Calendar.getInstance();
        List<Date> times = new ArrayList<>();

        JPanel currTimePanel = new JPanel();
        currTimePanel.setLayout(new BoxLayout(currTimePanel, BoxLayout.Y_AXIS));

        SimpleDateFormat dateFormat = null;

        NutritionController nutritionController = new NutritionController();

        // Get timeline dates
        switch (viewType) {
            case "Day":
                c.set(Calendar.MINUTE, 0);
                String[] timeNames = new String[]{"Early Morning", "Morning", "Noon",
                    "Afternoon", "Evening", "Late Night"};

                // Add time names to time panel
                for (String t : timeNames) {
                    TimelineDropdown dropdown = new TimelineDropdown(
                        new String[]{t}
                    );

                    Date startDate = c.getTime();
                    c.add(Calendar.HOUR, 4);
                    Date endDate = c.getTime();

                    // Get nutritional items in select hours
                    List<NutritionInstance> items = nutritionController.getItems(startDate, endDate);

                    for (NutritionInstance item : items) {
                        // TODO: get food by id, add to dropdown with quantity
                        // dropdown.add();
                    }

                    currTimePanel.add(dropdown);
                }

                break;
            case "Week":
                c.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().getFirstDayOfWeek());
                for (int i = 0; i < 7; i++) {
                    c.add(Calendar.DAY_OF_MONTH, 1);
                    times.add(c.getTime());
                }

                dateFormat = new SimpleDateFormat("MMM dd EE");
                break;
            case "Month":
                c.set(Calendar.DAY_OF_MONTH, 1);

                // Get first Sunday
                int nDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    c.add(Calendar.DATE, -1);
                    nDays++;
                }

                // Get each week until end of month
                for (int i = 1; i <= nDays; i += 7) {
                    times.add(c.getTime());
                    c.add(Calendar.DATE, 7);
                }

                dateFormat = new SimpleDateFormat("'Week' MM/dd/yyyy");
                break;
        }

        // Parse calendar dates if not day timeline
        if (!viewType.equals("Day")) {
            for (Date time : times) {
                String formattedTime = dateFormat.format(time);

                // TODO: add nutrition instances to each corresponding time
                TimelineDropdown dropdown = new TimelineDropdown(
                    new String[]{formattedTime}
                );

                currTimePanel.add(dropdown);
            }
        }

        currTimeline.add(currTimePanel);

        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                StandardForm form = new AddNutritionForm("Add Nutrition Item");
                form.setLocationRelativeTo(this);
                form.setVisible(true);
                break;
            default:
                super.actionPerformed(e);
                break;
        }
    }
}