package org.boothverse.foodpants.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.ui.controllers.FoodController;

import javax.measure.Quantity;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public class Goal<Q extends Quantity<Q>> extends IdObject {
    private static Logger logger = LogManager.getLogger(Goal.class);
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
        logger.info("Checking whether goal of type " + goalType.name() + " and quantity " + dailyQuantity + " has been attained with " + actualQuantity);
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
