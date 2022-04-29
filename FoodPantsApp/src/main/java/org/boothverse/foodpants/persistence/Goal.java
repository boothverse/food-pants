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

    /**
     * Creates a new Goal
     *
     * @param id
     * @param goalType
     * @param dailyQuantity
     * @param nutritionType
     */
    public Goal(String id, GoalType goalType, Quantity<Q> dailyQuantity, NutritionType nutritionType) {
        super(id);
        this.goalType = goalType;
        this.dailyQuantity = dailyQuantity;
        this.nutritionType = nutritionType;
    }

    /**
     * Determines whether this goal has been attained
     *
     * @param actualQuantity
     * @return
     */
    public boolean isAttained(Quantity<Q> actualQuantity) {
        return dailyQuantity.subtract(actualQuantity).getValue().doubleValue() <= 0;
    }
}
