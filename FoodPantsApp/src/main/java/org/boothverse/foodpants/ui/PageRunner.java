package org.boothverse.foodpants.ui;

import lombok.Getter;
import org.boothverse.foodpants.ui.components.PageViewer;
import org.boothverse.foodpants.ui.components.Navbar;
import org.boothverse.foodpants.ui.pages.Page;

import javax.swing.*;
import java.awt.*;

public class PageRunner {
    private static final Dimension MIN_SIZE = new Dimension(1000, 500);
    @Getter
    private static JFrame frame;
    private static PageViewer pageFrame;

    private static void setupWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(MIN_SIZE);
        frame.setTitle("FoodPants");
        frame.setVisible(true);
        frame.setIconImage(ImageIconGenerator.logo.getImage());
        frame.setLayout(new BorderLayout());
    }

    private static void setupChildren() {
        // Add Navbar
        Navbar nav = new Navbar();
        frame.add(nav, BorderLayout.WEST);

        // Add Interface Panel (scroll pane)
        pageFrame = new PageViewer(new Page());
        frame.add(pageFrame);

        // Set page to pantry on load
        PageManager.setPage("Pantry");
    }

    private static void createAndShowGUI() {
        Style.setupLookAndFeel();
        setupWindow();
        setupChildren();
    }

    public static PageViewer getPageViewer() {
        return pageFrame;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(PageRunner::createAndShowGUI);
    }
}
