package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
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
        String raw = jsonParser.getValueAsString();

        System.out.println(raw);

        try {
            Quantities.getQuantity(raw);
        } catch (Exception e) {
            return Quantities.getQuantity("0 g");
        }

        return Quantities.getQuantity(raw);
    }
}
