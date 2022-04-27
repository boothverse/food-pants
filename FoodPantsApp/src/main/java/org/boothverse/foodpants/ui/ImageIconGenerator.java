package org.boothverse.foodpants.ui;

import javax.swing.*;

public class ImageIconGenerator {
    public static String path = "target/classes/images/";
    public static String foodGroupPath = "target/classes/images/foodGroups";

    // Checkmark icons
    public static ImageIcon unselected = new ImageIcon(path + "checkmark/uncheck.png");
    public static ImageIcon selected = new ImageIcon(path + "checkmark/check.png");

    public static ImageIcon timeline = new ImageIcon(path + "icons/timeline.png");
    public static ImageIcon logo = new ImageIcon(path + "icons/logo.png");

    // Food Group Icons
    public static ImageIcon fruit = new ImageIcon(foodGroupPath + "fruit.png");
    public static ImageIcon vegetable = new ImageIcon(foodGroupPath + "vegetable.png");
    public static ImageIcon meat = new ImageIcon(foodGroupPath + "meat.png");
    public static ImageIcon dairy = new ImageIcon(foodGroupPath + "dairy.png");
    public static ImageIcon grain = new ImageIcon(foodGroupPath + "grain.png");
    public static ImageIcon drink = new ImageIcon(foodGroupPath + "drink.png");
    public static ImageIcon sugar = new ImageIcon(foodGroupPath + "sugar.png");
    public static ImageIcon fat = new ImageIcon(foodGroupPath + "fat.png");
    public static ImageIcon flavoring = new ImageIcon(foodGroupPath + "flavoring.png");
    public static ImageIcon other = new ImageIcon(foodGroupPath + "other.png");
}
