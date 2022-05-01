package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;
import java.util.Objects;

@NoArgsConstructor
public class Food extends IdObject {
    @Getter
    protected String name;
    @Getter
    protected FoodGroup foodGroup;
    @Getter @Setter
    protected NutritionDescriptor nutrition;

    /**
     * Creates a new Food
     *
     * @param id
     * @param name
     * @param foodGroup
     * @param nutrition
     */
    public Food(String id, String name, FoodGroup foodGroup, NutritionDescriptor nutrition) {
        super(id);
        this.name = name;
        this.foodGroup = foodGroup;
        this.nutrition = nutrition;
    }

    public FoodInstance createInstance(Quantity<?> quantity) {
        return new FoodInstance(id, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name) && foodGroup == food.foodGroup && Objects.equals(nutrition, food.nutrition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, foodGroup, nutrition);
    }
}
