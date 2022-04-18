package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import tec.units.ri.AbstractUnit;
import tec.units.ri.quantity.Quantities;

import javax.measure.Quantity;
import java.io.IOException;

public class QuantitySerializer extends StdSerializer<Quantity<?>> {

    public QuantitySerializer(Class<Quantity<?>> t) {
        super(t);
    }

    public QuantitySerializer() {
        this(null);
    }

    @Override
    public void serialize(Quantity<?> quantity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(quantity.toString());
    }
}
