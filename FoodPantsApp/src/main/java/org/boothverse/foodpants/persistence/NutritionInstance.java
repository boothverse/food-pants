package org.boothverse.foodpants.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.ui.controllers.FoodController;

import javax.measure.Quantity;
import java.util.Date;

/**
 * An object representing a nutrition instance
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NutritionInstance extends FoodInstance {
    private static Logger logger = LogManager.getLogger(NutritionInstance.class);
    @Getter
    private String foodId;
    @Getter @Setter
    private Date consumedAt;

    /**
     * Creates a new NutritionInstance
     *
     * @param id the id of the nutrition instance
     * @param foodId the nam of the food the nutrition instance i associated with
     * @param quantity the quantity of the nutrition instance
     * @param consumedAt when the food was consumed at
     */
    public NutritionInstance(String id, String foodId, Quantity<?> quantity, Date consumedAt) {
        super(id, quantity);
        this.foodId = foodId;
        this.consumedAt = consumedAt;
    }

    /**
     * Returns the NutritionInstance in a String format
     *
     * @return a string representing the instance
     */
    @Override
    public String toString() {
        logger.info("Returning nutrition instance details as string");
        return NutritionInstance.class.getSimpleName() + "(" +
            "id=" + getId() + ", " +
            "quantity=" + getQuantity() + ", " +
            "foodId=" + getFoodId() + ", " +
            "consumedAt=" + getConsumedAt() + ")";
    }
}
