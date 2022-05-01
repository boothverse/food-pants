package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.Unit;
import java.io.IOException;
import java.util.Arrays;

public class QuantityDeserializer extends StdDeserializer<Quantity<?>> {

    public QuantityDeserializer(Class<?> vc) {
        super(vc);
    }

    public QuantityDeserializer() {
        this(null);
    }

    @Override
    public Quantity<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String rawQuantity = jsonParser.getValueAsString();
        String[] parts = rawQuantity.split(" ");
        Unit<?> unit;
        if (parts.length == 1) {
            unit = AbstractUnit.ONE;
        } else {
            unit = AbstractUnit.parse(parts[1]);
        }
        return Quantities.getQuantity(Double.parseDouble(parts[0]), unit);
    }
}
