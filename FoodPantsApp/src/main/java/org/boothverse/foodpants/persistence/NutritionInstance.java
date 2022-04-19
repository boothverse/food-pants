package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;
import java.util.Date;

@NoArgsConstructor
public class NutritionInstance extends FoodInstance {
    @Getter
    private String foodId;
    @Getter @Setter
    private Date consumedAt;

    public NutritionInstance(String id, String foodId, Quantity<?> quantity, Date consumedAt) {
        super(id, quantity);
        this.foodId = foodId;
        this.consumedAt = consumedAt;
    }

    @Override
    public String toString() {
        return NutritionInstance.class.getSimpleName() + "(" +
            "id=" + getId() + ", " +
            "quantity=" + getQuantity() + ", " +
            "foodId=" + getFoodId() + ", " +
            "consumedAt=" + getConsumedAt() + ")";
    }
}
