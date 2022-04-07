package org.boothverse.foodpants.business.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.boothverse.foodpants.business.dao.serialization.QuantityMixin;
import org.boothverse.foodpants.business.dao.serialization.UnitMixin;

import javax.measure.Quantity;
import javax.measure.Unit;

public abstract class FileDAO {
    protected final String filename;
    protected final ObjectMapper mapper;

    public FileDAO(String filename) {
        this.filename = "db/" + filename;
        this.mapper = new ObjectMapper();
        mapper.addMixIn(Unit.class, UnitMixin.class);
        mapper.addMixIn(Quantity.class, QuantityMixin.class);
    }
}
