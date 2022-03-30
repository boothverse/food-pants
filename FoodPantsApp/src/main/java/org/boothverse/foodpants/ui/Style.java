package org.boothverse.foodpants.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Style {
    public static final Color RED = new Color(255, 84, 84);
    public static final Color SALMON = new Color(255, 132, 113);

    public static final Color BLACK = new Color(18, 19, 15);
    public static final Color GREY_0 = new Color(82,80,80);
    public static final Color GREY_1 = new Color(108,105,105);
    public static final Color GREY_2 = new Color(121, 119, 119);
    public static final Color GREY_3 = new Color(144, 142, 142);
    public static final Color GREY_4 = new Color(163, 161, 161);
    public static final Color GREY_5 = new Color(185, 182, 182);
    public static final Color GREY_6 = new Color(205, 202, 202);
    public static final Color GREY_7 = new Color(225, 222, 222);

    public static final Color PLATINUM = new Color(234, 230, 229);
    public static final Color LIGHT_PLATINUM = PLATINUM.brighter();
    public static final Color WHITE = new Color(255, 255, 255);

    public static final Color TRANSPARENT = new Color(1,1,1, 0);

    public static final Color PRESS_NAV_BUTTON = RED.brighter();

    public static final Border BORDER_1 = BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            BorderFactory.createEmptyBorder(5,5,5,5));

    private static final int headerSize = 32;
    private static final int bodySize = 14;

    public static final Font headerStyle = new Font("Arial", Font.BOLD, headerSize);
    public static final Font bodyStyle = new Font("Arial", Font.PLAIN, bodySize);
}
