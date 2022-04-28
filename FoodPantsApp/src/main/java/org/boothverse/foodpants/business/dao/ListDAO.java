package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.IdObject;

import java.util.Map;

public interface ListDAO<T extends IdObject> {

    void save(T data);

    Map<String, T> load();

    void remove(String id);

}
