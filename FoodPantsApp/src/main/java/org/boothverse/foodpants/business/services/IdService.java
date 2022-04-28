package org.boothverse.foodpants.business.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.NonNull;
import org.apache.commons.lang.RandomStringUtils;
import org.boothverse.foodpants.business.dao.*;
import org.boothverse.foodpants.persistence.*;

public class IdService {

    /** Holds all IDs registered by the service. */
    protected Set<String> ids = new HashSet<>();

    /**
     * Loads all IdObjects from the database and registers all IDs to the service.
     */
    public IdService() {
        registerIds(new FoodDAO().load());
        registerIds(new FoodInstanceDAO("shoppingList").load());
        registerIds(new FoodInstanceDAO("pantry").load());
        registerIds(new GoalDAO().load());
        registerIds(new NutritionInstanceDAO().load());
        registerIds(new RecipeDAO().load());
        registerIds(new ReportDAO().load());
    }

    /**
     * Generates an alphanumeric ID that is not already registered by the service,
     * and then registers the ID to the service.
     *
     * @return a new 20-character alphanumeric id
     */
    public String getId() {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(20);
        } while (ids.contains(id));
        ids.add(id);
        return id;
    }

    /**
     * Registers all of the ids of the given objects within the id service.
     *
     * @param data a map of [id, IdObject] pairs to register
     * @param <T> the type
     */
    private <T extends IdObject> void registerIds(@NonNull Map<String, T> data) {
        ids.addAll(data.keySet());
    }

}
