package org.boothverse.foodpants;

import org.boothverse.foodpants.business.dao.*;
import org.boothverse.foodpants.business.dao.util.JDBCUtils;
import org.boothverse.foodpants.persistence.IdObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for loading preset data
 */
public class LoadPresets {

    /**
     * Preset data is in "src/main/resources/sql/preset_[TABLE].sql"
     * Make your own additional preset files if you want more data, and set the relevant
     * boolean variable below to true.
     *
     * Set to true if you wanna use the presets
     */
    private static final boolean USER_PRESETS = true;
    private static final boolean FOOD_PRESETS = true;
    private static final boolean PANTRY_PRESETS = true;
    private static final boolean SHOPPING_LIST_PRESETS = true;
    private static final boolean GOAL_PRESETS = true;
    private static final boolean NUTRITION_INSTANCE_PRESETS = true;
    private static final boolean RECIPE_PRESETS = true;
    private static final boolean REPORT_PRESETS = true;
    /*********************************************/

    public static void main(String[] args) {
        loadPresets();
    }

    public static void loadPresets() {
        if (USER_PRESETS) loadPresets("users");
        List<ListDAO<?>> listDAOs = new ArrayList<>();
        if (FOOD_PRESETS) listDAOs.add(new FoodDAO());
        if (GOAL_PRESETS) listDAOs.add(new GoalDAO());
        if (NUTRITION_INSTANCE_PRESETS) listDAOs.add(new NutritionInstanceDAO());
        if (RECIPE_PRESETS) listDAOs.add(new RecipeDAO());
        if (REPORT_PRESETS) listDAOs.add(new ReportDAO());
        listDAOs.forEach(LoadPresets::loadPresets);

        if (PANTRY_PRESETS) loadPresets(new FoodInstanceDAO("pantry"), "pantry");
        if (SHOPPING_LIST_PRESETS) loadPresets(new FoodInstanceDAO("shoppingList"), "shoppingList");
    }

    private static <T extends IdObject> void loadPresets(ListDAO<T> dao) {
        loadPresets(dao, dao.getClass().getSimpleName().replace("DAO", "") + "s");
    }

    private static <T extends IdObject> void loadPresets(ListDAO<T> dao, String table) {
        dao.removeAll();
        loadPresets(table);
    }

    private static void loadPresets(String table) {
        // TODO: update this to target before packaging
        String filepath = "src/main/resources/sql/preset_" + table.toLowerCase() + ".sql";
        System.out.println("Loading presets from " + filepath);
        new JDBCUtils().executeScript(filepath);
    }
}
