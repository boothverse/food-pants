package org.boothverse.foodpants.business.dao;

public interface SingleDAO<T> {

    public void save(T data);

    public T load();

}
