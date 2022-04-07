package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import javax.measure.Unit;
import java.io.IOException;

public class UnitSerializer extends StdSerializer<Unit<?>> {

    public UnitSerializer(Class<Unit<?>> t) {
        super(t);
    }

    public UnitSerializer() {
        this(null);
    }

    @Override
    public void serialize(Unit<?> unit, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(unit.toString());
    }
}
