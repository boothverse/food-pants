package org.boothverse.foodpants.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
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
        if(actualQuantity == null){
            return false;
        }
        if(this.goalType.equals(GoalType.MAXIMIZE)){
            return dailyQuantity.subtract(actualQuantity).getValue().doubleValue() >= 0;
        }
        else{
            return dailyQuantity.subtract(actualQuantity).getValue().doubleValue() <= 0;
        }
    }
}
