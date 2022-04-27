package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;

@NoArgsConstructor
@Getter @Setter
public class Goal<Q extends Quantity<Q>> extends IdObject {

    private GoalType goalType;
    private Quantity<Q> dailyQuantity;
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
