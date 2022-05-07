package org.boothverse.foodpants.persistence;

import lombok.*;

import javax.measure.Quantity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class NutritionDescriptor {
    private Map<NutritionType, Quantity<?>> nutritionInfo = new HashMap<>();
    private Quantity<?> servingSize;
}
