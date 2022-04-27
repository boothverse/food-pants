package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = QuantityDeserializer.class)
public abstract class QuantityMixin {
}
