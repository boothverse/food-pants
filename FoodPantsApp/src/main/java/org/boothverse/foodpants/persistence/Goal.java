package org.boothverse.foodpants.persistence;

import javax.measure.Quantity;

public class Goal extends IdObject {
  private GoalType goalType;
  private Quantity dailyQuantity;

  public Goal(String id, GoalType goalType, Quantity dailyQuantity) {
    super(id);
    this.goalType = goalType;
    this.dailyQuantity = dailyQuantity;
  }
}
