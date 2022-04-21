package org.boothverse.foodpants.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatSystemProperties;
import org.boothverse.foodpants.ui.theme.FoodPantsLaf;

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
    //public static final Color LIGHT_PLATINUM = PLATINUM.brighter();
    //public static final Color WHITE = new Color(255, 255, 255);

    public static final Color TRANSPARENT = new Color(1, 1, 1, 0);

    public static final Color PRESS_NAV_BUTTON = RED.brighter();

    private static final int headerSize = 32;
    private static final int bodySize = 14;

    //public static final Font headerStyle = new Font("Arial", Font.BOLD, headerSize);

    public static void setupLookAndFeel() {
        FlatLaf.registerCustomDefaultsSource( "org.boothverse.foodpants.ui.theme" );
        if( System.getProperty( FlatSystemProperties.UI_SCALE ) == null ) {
            String scaleFactor = System.getProperty("sun.java2d.uiScale");
            if( scaleFactor != null )
                System.setProperty( FlatSystemProperties.UI_SCALE, scaleFactor );
        }

        setupLafProperties();
        FoodPantsLaf.setup();
    }

    private static void setupLafProperties() {
        FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#ff5454"));
    }
}