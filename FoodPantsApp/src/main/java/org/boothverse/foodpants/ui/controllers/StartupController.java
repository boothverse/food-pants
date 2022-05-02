package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.User;

import java.util.Map;

public class StartupController {
    /**
     * Registers the user.
     *
     * @param name
     * @param info
     */
    public void register(String name, Map<String, String> info) {
        User user = Services.USER_SERVICE.register(name, info);
        Services.NUTRITION_SERVICE.getRecommendedGoal(user);
    }
}
