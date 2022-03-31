package org.boothverse.foodpants.ui;

import org.boothverse.foodpants.ui.components.PageViewer;
import org.boothverse.foodpants.ui.pages.*;

public class PageManager {
    private static Page activePage;

    private static final Page pantryPage = new PantryPage();
    private static final Page recipePage = new RecipePage();
    private static final Page shoppingPage = new ShoppingPage();
    //Three subpages of NutritionPage, timeline is default
    private static final Page timelinePage = new NutritionTimelinePage();
    private static final Page goalsPage = new NutritionGoalsPage();
    private static final Page reportPage = new NutritionReportsPage();

    public static void setPage(String pageName) {
        PageViewer pageViewer = PageRunner.getPageViewer();

        switch (pageName) {
            case "Pantry" -> activePage = pantryPage;
            case "Recipes" -> activePage = recipePage;
            case "Shopping List" -> activePage = shoppingPage;
            case "Nutrition" -> activePage = timelinePage;
            case "Goals" -> activePage = goalsPage;
            case "Report" -> activePage = reportPage;
        }
        pageViewer.setViewportView(activePage);
    }
}
