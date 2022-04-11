package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.measure.Quantity;
import java.util.Date;

public class NutritionInstance extends FoodInstance {
    @Getter
    private final String foodId;
    @Getter @Setter
    private Date consumedAt;

    public NutritionInstance(String id, String foodId, Quantity<?> quantity, Date consumedAt) {
        super(id, quantity);
        this.foodId = foodId;
        this.consumedAt = consumedAt;
    }
}
