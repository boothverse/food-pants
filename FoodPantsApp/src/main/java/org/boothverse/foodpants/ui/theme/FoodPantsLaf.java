package org.boothverse.foodpants.ui.theme;

import com.formdev.flatlaf.FlatLightLaf;

public class FoodPantsLaf extends FlatLightLaf {
    public static boolean setup() {
        return setup(new FlatLightLaf());
    }

    @Override
    public String getName() { return "FoodPantsLaf"; }
}
