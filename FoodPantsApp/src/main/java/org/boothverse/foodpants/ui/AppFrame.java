package org.boothverse.foodpants.ui;

import org.boothverse.foodpants.ui.components.Navbar;
import org.boothverse.foodpants.ui.pages.StandardPage;

import javax.swing.*;
import java.awt.*;

public class AppFrame {
    private static final Dimension MIN_SIZE = new Dimension(800, 500);
    private static JFrame frame;

    private static void setupWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(MIN_SIZE);
        frame.setTitle("FoodPants");
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
    }

    private static void setupChildren() {
        // Add Navbar
        Navbar nav = new Navbar();
        frame.add(nav, BorderLayout.WEST);

        // Add Interface Panel
        InterfacePanel interPanel = new InterfacePanel(new StandardPage());
        frame.add(interPanel);
    }

    private static void createAndShowGUI() {
        setupWindow();
        setupChildren();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(AppFrame::createAndShowGUI);
    }

}
