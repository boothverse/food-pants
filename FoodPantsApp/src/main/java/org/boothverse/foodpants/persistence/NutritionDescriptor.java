package org.boothverse.foodpants.persistence;

import lombok.*;

import javax.measure.Quantity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class NutritionDescriptor {
    private Map<NutritionType, Quantity<?>> nutritionInfo = new HashMap<>();
    private Quantity<?> servingSize;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutritionDescriptor that = (NutritionDescriptor) o;
        return Objects.equals(nutritionInfo, that.nutritionInfo) && Objects.equals(servingSize, that.servingSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nutritionInfo, servingSize);
    }
}
