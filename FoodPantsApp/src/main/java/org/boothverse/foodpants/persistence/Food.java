package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.Setter;

public class Food extends IdObject {
  @Getter
  private final String name;
  @Getter
  private final FoodGroup foodGroup;
  @Getter @Setter
  private NutritionDescriptor nutrition;

  public Food(String id, String name, FoodGroup foodGroup, NutritionDescriptor nutrition) {
    super(id);
    this.name = name;
    this.foodGroup = foodGroup;
    this.nutrition = nutrition;
  }
}
