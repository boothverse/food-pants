package org.boothverse.foodpants.ui;

import org.boothverse.foodpants.ui.components.PageViewer;
import org.boothverse.foodpants.ui.components.Navbar;
import org.boothverse.foodpants.ui.pages.Page;

import javax.swing.*;
import java.awt.*;

public class PageRunner {
    private static final Dimension MIN_SIZE = new Dimension(800, 500);
    private static JFrame frame;
    private static PageViewer pageFrame;

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

        // Add Interface Panel (scroll pane)
        pageFrame = new PageViewer(new Page());
        frame.add(pageFrame);
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unable to set LookAndFeel");
        }

        setupWindow();
        setupChildren();
    }

    public static PageViewer getPageViewer() { return pageFrame; }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(PageRunner::createAndShowGUI);
    }

}
