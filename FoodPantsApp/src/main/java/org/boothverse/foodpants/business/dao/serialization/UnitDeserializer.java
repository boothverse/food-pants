package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tec.units.ri.AbstractUnit;

import javax.measure.Unit;
import java.io.IOException;

public class UnitDeserializer extends StdDeserializer<Unit<?>> {

    public UnitDeserializer(Class<?> vc) {
        super(vc);
    }

    public UnitDeserializer() {
        this(null);
    }

    @Override
    public Unit<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String raw = jsonParser.getText();
        return AbstractUnit.parse(raw);
    }
}
