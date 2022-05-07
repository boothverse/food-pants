package org.boothverse.foodpants.business.dao;

/**
 * An interface with methods used in daos
 *
 * @param <T> the type of object the dao is operating on
 */
public interface SingleDAO<T> {

    /**
     * saves the given data object into its respective database
     *
     * @param data
     */
    public void save(T data);

    /**
     * loads the data from the database
     *
     * @return the loaded data object
     */
    public T load();

}
