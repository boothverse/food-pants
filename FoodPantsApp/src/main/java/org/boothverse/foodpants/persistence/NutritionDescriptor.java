package org.boothverse.foodpants.persistence;

import javax.measure.Quantity;
import java.util.Map;

public class NutritionDescriptor {
  private Map<NutritionType, Quantity> nutritionInfo;
  private Quantity servingSize;
}
