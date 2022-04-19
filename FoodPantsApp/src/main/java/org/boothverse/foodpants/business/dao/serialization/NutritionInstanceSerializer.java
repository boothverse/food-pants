package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.json.JSONObject;

import java.io.IOException;

public class NutritionInstanceSerializer extends StdSerializer<NutritionInstance> {

    public NutritionInstanceSerializer(Class<NutritionInstance> t) {
        super(t);
    }

    public NutritionInstanceSerializer() {
        this(null);
    }

    @Override
    public void serialize(NutritionInstance n, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JSONObject jo = new JSONObject();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", n.getId());
        jsonGenerator.writeObjectField("quantity", n.getQuantity());
        jsonGenerator.writeStringField("foodId", n.getFoodId());
        jsonGenerator.writeNumberField("consumedAt", n.getConsumedAt().getTime());
        jsonGenerator.writeEndObject();
    }
}
