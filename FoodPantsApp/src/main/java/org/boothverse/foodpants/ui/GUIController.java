package org.boothverse.foodpants.ui;

import org.boothverse.foodpants.ui.components.PageViewer;
import org.boothverse.foodpants.ui.pages.*;

public class GUIController {
    private static Page activePage;

    private static final Page pantryPage = new PantryPage();
    private static final Page nutritionPage = new NutritionPage();
    private static final Page recipePage = new RecipePage();
    private static final Page shoppingPage = new ShoppingPage();

    public static void setPage(String pageName) {
        PageViewer pageViewer = AppFrame.getPageViewer();

        switch (pageName) {
            case "Pantry" -> activePage = pantryPage;
            case "Recipes" -> activePage = recipePage;
            case "Nutrition" -> activePage = nutritionPage;
            case "Shopping List" -> activePage = shoppingPage;
        }
        pageViewer.setViewportView(activePage);
    }
}
