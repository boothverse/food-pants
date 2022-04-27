package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.IdObject;

import java.util.Map;

public interface ListDAO<T extends IdObject> {

    public void save(T data);

    public Map<String, T> load();

}
