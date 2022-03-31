package org.boothverse.foodpants.ui.pages;

public class NutritionReportsPage extends NutritionPage {

    public NutritionReportsPage() {
        //initTimeView();
    }

    /*
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
     */
}
