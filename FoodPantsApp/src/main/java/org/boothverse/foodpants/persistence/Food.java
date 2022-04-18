package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;

@NoArgsConstructor
public class Food extends IdObject {
    @Getter
    protected String name;
    @Getter
    protected FoodGroup foodGroup;
    @Getter @Setter
    protected NutritionDescriptor nutrition;

    public Food(String id, String name, FoodGroup foodGroup, NutritionDescriptor nutrition) {
        super(id);
        this.name = name;
        this.foodGroup = foodGroup;
        this.nutrition = nutrition;
    }

    public FoodInstance createInstance(Quantity quantity) {
        return new FoodInstance(id, quantity);
    }
}
