package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.util.QuantityUtils;

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
        String rawQuantity = jsonParser.getValueAsString();
        try {
            return QuantityUtils.parse(rawQuantity);
        } catch (PantsNotParsedException e) {
            throw new IOException(e);
        }
    }
}
