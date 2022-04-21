package org.boothverse.foodpants.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import org.boothverse.foodpants.ui.themes.FoodPantsLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class Style {
    public static final Color RED = new Color(255, 84, 84);

    public static final Color BLACK = new Color(18, 19, 15);
    public static final Color GREY_0 = new Color(82, 80, 80);
    public static final Color GREY_1 = new Color(108, 105, 105);
    public static final Color GREY_3 = new Color(144, 142, 142);

    public static final Color PLATINUM = new Color(234, 230, 229);

    public static final Color TRANSPARENT = new Color(1, 1, 1, 0);

    public static final Color PRESS_NAV_BUTTON = RED.brighter();

    public static Font headerStyle;

    public static void setupLookAndFeel() {
        if( System.getProperty( FlatSystemProperties.UI_SCALE ) == null ) {
            String scaleFactor = System.getProperty("sun.java2d.uiScale");
            if( scaleFactor != null )
                System.setProperty( FlatSystemProperties.UI_SCALE, scaleFactor );
        }

        setupLafProperties();
        FoodPantsLaf.setup();

        getLafData();
    }

    private static void getLafData() {
        headerStyle = FlatUIUtils.nonUIResource(UIManager.getFont( "h1.font"));
    }

    private static void setupLafProperties() {
        FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#ff5454"));
    }
}