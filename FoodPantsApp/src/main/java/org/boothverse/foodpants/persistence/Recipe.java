package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.boothverse.foodpants.business.services.Services;
import tec.units.ri.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import java.util.Date;
import java.util.List;

import static tec.units.ri.AbstractUnit.ONE;

@NoArgsConstructor
@Getter @Setter
public class Recipe extends Food {

    private String instructions;
    private List<FoodInstance> ingredients;
    private Double servings;

    public Recipe(String id, String name, FoodGroup foodGroup, NutritionDescriptor nutrition, String instructions, List<FoodInstance> ingredients, Double servings) {
        super(id, name, foodGroup, nutrition);
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.servings = servings;
    }

    public FoodInstance createFoodInstance(Double instanceServings) {
        return new FoodInstance(id, Quantities.getQuantity(instanceServings / this.servings, ONE));
    }

    public NutritionInstance createNutritionInstance(Double instanceServings) {
        String id = Services.ID_SERVICE.getId();
        String foodId = this.id;
        Quantity<Dimensionless> quantity = Quantities.getQuantity(instanceServings / this.servings, ONE);
        Date consumedAt = new Date();

        return new NutritionInstance(id, foodId, quantity, consumedAt);
    }
}
