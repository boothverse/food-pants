package org.boothverse.foodpants.ui;

import javax.swing.*;
import java.awt.*;

public class AppFrame {
    private static JFrame frame;
    private static final Dimension MIN_SIZE = new Dimension(800, 500);

    private static void setupWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(MIN_SIZE);
        frame.setTitle("FoodPants");
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
    }

    private static void createAndShowGUI() {
        setupWindow();

        // Add Navbar
        Navbar nav = new Navbar();
        frame.add(nav, BorderLayout.WEST);

        // Add Scrollpane
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(AppFrame::createAndShowGUI);
    }

}
