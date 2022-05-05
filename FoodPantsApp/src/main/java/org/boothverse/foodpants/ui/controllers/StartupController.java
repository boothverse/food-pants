package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.User;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.util.Date;
import java.util.Map;

public class StartupController {
    /**
     * Registers the user.
     *
     * @param name
     * TODO finish these javadocs...
     */
    public void register(String name, String gender, Quantity<Length> height, Quantity<Mass> weight, Date dob) {
        Services.USER_SERVICE.register(name, gender, height, weight, dob);
    }

    public boolean userExists() {
        if (Services.USER_SERVICE.getUser() != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
