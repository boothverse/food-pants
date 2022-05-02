package org.boothverse.foodpants.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;
import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode
public class NutritionInstance extends FoodInstance {
    @Getter
    private String foodId;
    @Getter @Setter
    private Date consumedAt;

    /**
     * Creates a new NutritionInstance
     *
     * @param id
     * @param foodId
     * @param quantity
     * @param consumedAt
     */
    public NutritionInstance(String id, String foodId, Quantity<?> quantity, Date consumedAt) {
        super(id, quantity);
        this.foodId = foodId;
        this.consumedAt = consumedAt;
    }

    /**
     * Returns the NutritionInstance in a String format
     *
     * @return
     */
    @Override
    public String toString() {
        return NutritionInstance.class.getSimpleName() + "(" +
            "id=" + getId() + ", " +
            "quantity=" + getQuantity() + ", " +
            "foodId=" + getFoodId() + ", " +
            "consumedAt=" + getConsumedAt() + ")";
    }
}
