package org.boothverse.foodpants.business.dao.serialization;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = UnitSerializer.class)
@JsonDeserialize(using = UnitDeserializer.class)
public abstract class UnitMixin {
}
