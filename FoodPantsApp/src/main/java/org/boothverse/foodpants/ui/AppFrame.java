package org.boothverse.foodpants.ui;

import javax.swing.*;

public class AppFrame {
    private static JFrame frame;

    private static void setupWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }

    private static void createAndShowGUI() {
        setupWindow();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(AppFrame::createAndShowGUI);
    }

}
