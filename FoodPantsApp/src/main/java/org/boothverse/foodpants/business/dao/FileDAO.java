package org.boothverse.foodpants.business.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.boothverse.foodpants.business.dao.serialization.QuantityMixin;
import org.boothverse.foodpants.business.dao.serialization.UnitMixin;

import javax.measure.Quantity;
import javax.measure.Unit;

public abstract class FileDAO<T> {
    protected final String filename;
    protected final ObjectMapper mapper;
    protected final Class<T> type;

    public FileDAO(Class<T> t, String filename) {
        type = t;
        this.filename = "db/" + filename;
        mapper = new ObjectMapper();
        mapper.addMixIn(Unit.class, UnitMixin.class);
        mapper.addMixIn(Quantity.class, QuantityMixin.class);
    }
}
