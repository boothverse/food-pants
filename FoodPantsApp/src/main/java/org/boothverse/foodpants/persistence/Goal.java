package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.measure.Quantity;

public class Goal<Q extends Quantity<Q>> extends IdObject {

  @Getter @Setter
  private GoalType goalType;
  @Getter @Setter
  private Quantity<Q> dailyQuantity;
  @Getter @Setter
  private NutritionType nutritionType;

  public Goal(String id, GoalType goalType, Quantity<Q> dailyQuantity, NutritionType nutritionType) {
    super(id);
    this.goalType = goalType;
    this.dailyQuantity = dailyQuantity;
    this.nutritionType = nutritionType;
  }

  public boolean isAttained(Quantity<Q> actualQuantity) {
    return dailyQuantity.subtract(actualQuantity).getValue().doubleValue() <= 0;
  }
}
