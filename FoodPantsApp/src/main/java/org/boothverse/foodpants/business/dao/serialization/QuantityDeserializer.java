package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tec.units.ri.AbstractUnit;
import tec.units.ri.quantity.Quantities;

import javax.measure.Quantity;
import java.io.IOException;

public class QuantityDeserializer extends StdDeserializer<Quantity<?>> {

    public QuantityDeserializer(Class<?> vc) {
        super(vc);
    }

    public QuantityDeserializer() {
        this(null);
    }

    @Override
    public Quantity<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        jsonParser.nextValue();
        String rawUnit = jsonParser.getValueAsString();
        jsonParser.nextValue();
        Double rawValue = jsonParser.getValueAsDouble();

        return Quantities.getQuantity(rawValue, AbstractUnit.parse(rawUnit));
    }
}
