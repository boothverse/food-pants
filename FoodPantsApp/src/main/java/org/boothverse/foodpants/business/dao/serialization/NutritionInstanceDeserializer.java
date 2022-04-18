package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.boothverse.foodpants.persistence.NutritionInstance;
import tec.units.ri.AbstractUnit;
import tec.units.ri.quantity.Quantities;

import javax.measure.Quantity;
import java.io.IOException;
import java.util.Date;

public class NutritionInstanceDeserializer extends StdDeserializer<NutritionInstance> {

    public NutritionInstanceDeserializer(Class<?> vc) {
        super(vc);
    }

    public NutritionInstanceDeserializer() {
        this(null);
    }

    @Override
    public NutritionInstance deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        jsonParser.nextValue();
        String id = jsonParser.getValueAsString();
        jsonParser.nextValue();
        Quantity<?> quantity = jsonParser.readValueAs(Quantity.class);
        jsonParser.nextValue();
        String foodId = jsonParser.getValueAsString();
        jsonParser.nextValue();
        long rawDate = jsonParser.getValueAsLong();

        NutritionInstance n = new NutritionInstance(id, foodId, quantity, new Date(rawDate));
        System.out.println(n);
        return n;
    }
}
