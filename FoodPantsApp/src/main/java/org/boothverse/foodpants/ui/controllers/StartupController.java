package org.boothverse.foodpants.ui.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.User;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.util.Date;
import java.util.Map;

public class StartupController {
    private static Logger logger = LogManager.getLogger(StartupController.class);

    /**
     * Registers the user.
     *
     * @param name
     * TODO finish these javadocs...
     */
    public void register(String name, String gender, Quantity<Length> height, Quantity<Mass> weight, Date dob) {
        Services.USER_SERVICE.register(name, gender, height, weight, dob);
        logger.info(name + " registered");
    }

    public boolean userExists() {
        if (Services.USER_SERVICE.getUser() != null) {
            logger.info("user exists");
            return true;
        }
        else {
            logger.info("user does not exist");
            return false;
        }
    }
}
