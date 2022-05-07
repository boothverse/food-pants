package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.IdObject;

import java.util.Map;

/**
 * An interface with methods used in daos
 *
 * @param <T> the type of object the dao is operating on
 */
public interface ListDAO<T extends IdObject> {

    /**
     * saves the given data object into its respective database
     *
     * @param data
     */
    void save(T data);

    /**
     * loads the data from the database
     *
     * @return a map of data objects and their ids
     */
    Map<String, T> load();

    /**
     * removes the object with the specified id from the database
     *
     * @param id the specified id
     */
    void remove(String id);

    /**
     * removes all the items from the database
     */
    void removeAll();

}
