package org.boothverse.foodpants.business.services;

import java.util.HashSet;
import java.util.Set;

public class IdService {

    /** Holds all IDs registered by the service. */
    protected Set<String> ids = new HashSet<>();

    /**
     * Loads all IdObjects from the database and registers all IDs to the service.
     */
    public IdService() {

    }

    /**
     * Generates an alphanumeric ID that is not already registered by the service.
     *
     * @return
     */
    public String getId() {
        return null;
    }
}
