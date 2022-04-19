package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = QuantityDeserializer.class)
//@JsonSerialize(using = QuantitySerializer.class)
public abstract class QuantityMixin {
}
