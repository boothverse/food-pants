package org.boothverse.foodpants.ui.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.User;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.PageRunner;
import org.boothverse.foodpants.ui.components.Navbar;
import org.boothverse.foodpants.ui.forms.NewUserForm;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Map;

/**
 * Facilitates application startup
 */
public class StartupController {
    private static Logger logger = LogManager.getLogger(StartupController.class);
    private NewUserForm form;

    /**
     * Registers the user.
     *
     * @param name the users name
     * @param gender the users gender
     * @param height the users height
     * @param weight the users weight
     * @param dob the users dob
     */
    public void register(String name, String gender, Quantity<Length> height, Quantity<Mass> weight, Date dob) {
        Services.USER_SERVICE.register(name, gender, height, weight, dob);
        logger.info(name + " registered");
        walkthrough();
    }

    /**
     * Determines whether there is a registered user
     *
     * @return whether there is a registered user
     */
    public boolean userExists() {
        if (Services.USER_SERVICE.getUser() != null) {
            logger.info("user exists");
            return true;
        }
        else {
            logger.error("user does not exist");
            return false;
        }
    }

    public void walkthrough() {
        Component frame = PageRunner.getFrame();

        int choice = JOptionPane.showConfirmDialog(frame, "Would you like to view the user walk-through?", "Begin walkthrough?", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (form != null) {
                frame.setVisible(true);
                frame.setEnabled(true);
                form.setVisible(false);
                form.setEnabled(false);
                form.dispose();
            }
            JOptionPane.showMessageDialog(frame, "Welcome to FoodPants! Here you can manage a digital pantry, a recipe list, a nutrition log, and a shopping list!");

            Navbar.getNavButtons()[0].doClick();
            JOptionPane.showMessageDialog(frame, "This is the Pantry. Click the \"+\" button to add a food that you have in your actual pantry, and then you can consume the item to add it to your nutrition log");
            JOptionPane.showMessageDialog(frame, "Click the modify button to edit or delete foods, and the search button to look for a specific food");

            Navbar.getNavButtons()[1].doClick();
            JOptionPane.showMessageDialog(frame, "This is the recipe page. Here you can click the \"+\" button to add a recipe.");
            JOptionPane.showMessageDialog(frame, "You can also click recommend to get recipes based on what items you have in your pantry");
            JOptionPane.showMessageDialog(frame, "Additionally, you can also search the recipe page based on recipe names.");

            Navbar.getNavButtons()[2].doClick();
            JOptionPane.showMessageDialog(frame, "This is the nutrition log. Click \"+\" to log an item you have eaten, and tab between the time periods at the top of the screen to see different intervals of food you have eaten in the last day/week/month");
            JOptionPane.showMessageDialog(frame, "You can also click on goals to set a goal for yourself about certain nutrition info to meet or not exceed, or Report to generate a report for a specified time period of what you have eaten.");
            JOptionPane.showMessageDialog(frame, "You can similarly modify foods here as on the upcoming shopping page.");

            Navbar.getNavButtons()[3].doClick();
            JOptionPane.showMessageDialog(frame, "Finally, this is the shopping page. This allows you to put items you want to buy on a list to take to the store with you");
            JOptionPane.showMessageDialog(frame, "Check off items you have bought, and export your list to a PDF using the export button.");
            JOptionPane.showMessageDialog(frame, "Press mark all to check off all items, and New to create and entirely new list. Finally, click purchase to add all checked items to your digital pantry!");


            Navbar.getNavButtons()[0].doClick();
            JOptionPane.showMessageDialog(frame, "Thanks for viewing the walk through!");
            frame.setVisible(true);
        }
        else {
            frame.setVisible(true);
        }
    }

    public void beginRegistration() {
        Component frame = PageRunner.getFrame();
        if (!userExists()) {
            form = new NewUserForm();
            form.setLocationRelativeTo(frame);
            form.setVisible(true);
        }
        else {
            frame.setVisible(true);
        }
    }
}
