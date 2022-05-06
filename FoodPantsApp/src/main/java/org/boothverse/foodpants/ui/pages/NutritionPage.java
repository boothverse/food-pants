package org.boothverse.foodpants.ui.pages;

import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NutritionPage extends Page {
    protected static final String[] labels = {"+", "Timeline", "Goals", "Report", "Modify"};

    private static boolean modifyingPage;

    public NutritionPage() {
        super(labels);
    }

    public static boolean isModifyingPage() {
        return modifyingPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Timeline":
                PageManager.setPage("Nutrition");
                break;
            case "Goals":
                PageManager.setPage("Goals");
                break;
            case "Report":
                PageManager.setPage("Report");
                break;
            case "Modify":
                JButton modifyBtn = (JButton) e.getSource();
                modifyingPage = !modifyingPage;

                if (modifyingPage) {
                    modifyBtn.setBackground(Style.GREY_0);
                } else {
                    modifyBtn.setBackground(Style.GREY_1);
                }

                PageManager.getActivePage().notifyChange("modify", null, null);
                break;
        }
    }
}
