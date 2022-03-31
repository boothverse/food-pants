package org.boothverse.foodpants.ui;

import javax.swing.*;

public class ImageIconGenerator {
    public static String path = "target/classes/images/";

    // Checkmark icons
    public static ImageIcon unselected = new ImageIcon(path + "checkmark/uncheck.png");
    public static ImageIcon selected = new ImageIcon(path + "checkmark/check.png");

    // Food icons
    public static ImageIcon banana = new ImageIcon(path + "icons/banana.png");
    public static ImageIcon bread = new ImageIcon(path + "icons/bread.png");
    public static ImageIcon calories = new ImageIcon(path + "icons/calories.png");
    public static ImageIcon cheese = new ImageIcon(path + "icons/cheese.png");
    public static ImageIcon lettuce = new ImageIcon(path + "icons/lettuce.png");
    public static ImageIcon meat = new ImageIcon(path + "icons/meat.png");
}
