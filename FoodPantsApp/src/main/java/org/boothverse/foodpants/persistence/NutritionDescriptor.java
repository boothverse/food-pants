package org.boothverse.foodpants.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;
import java.util.Map;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class NutritionDescriptor {
    private Map<NutritionType, Quantity> nutritionInfo;
    private Quantity servingSize;
}
