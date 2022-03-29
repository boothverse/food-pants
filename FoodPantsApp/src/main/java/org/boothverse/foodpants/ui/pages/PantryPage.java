package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.PantryPanel;
import org.boothverse.foodpants.ui.forms.AddPantryForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PantryPage extends Page {
    private static final String[] labels = {"+", "Search", "Modify"};

    private static final String[] tempNames = {"Banana", "Muffin", "Apple"}; // FOR PROTOTYPE, REMOVE LATER
    private static final int[] tempQuants = {3, 1, 2}; // FOR PROTOTYPE, REMOVE LATER

    private static List<PantryPanel> pantryItems;

    private static boolean modifyingPantry;

    public PantryPage() {
        super(labels);
        pantryItems = new ArrayList<>();
        modifyingPantry = false;

        JPanel listWrapper = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new GridLayout(2, 2));

        for (int i = 0; i < tempNames.length; i++) {
            PantryPanel newItem = new PantryPanel(tempNames[i], tempQuants[i]);

            pantryItems.add(newItem);
            panel.add(newItem);
        }

        listWrapper.add(panel);
        add(listWrapper);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                new AddPantryForm("Add Item").setVisible(true);
                break;
            case "Search":
                JOptionPane.showInputDialog(null, "Search");
                break;
            case "Modify":
                JButton modifyBtn = (JButton) e.getSource();
                modifyingPantry = !modifyingPantry;

                if (modifyingPantry) {
                    modifyBtn.setBackground(Style.GREY_0);
                } else {
                    modifyBtn.setBackground(Style.GREY_1);
                }

                for (PantryPanel pantryPanel : pantryItems) {
                    pantryPanel.setModification(modifyingPantry);
                }

                break;
        }
    }
}
