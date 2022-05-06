package org.boothverse.foodpants.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.boothverse.foodpants.business.services.Services;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import java.util.Date;
import java.util.List;

import static tech.units.indriya.AbstractUnit.ONE;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public class Recipe extends Food {
    private String instructions;
    private List<FoodInstance> ingredients;
    private Double servings;

    /**
     * Creates a new Recipe
     *
     * @param id
     * @param name
     * @param foodGroup
     * @param nutrition
     * @param instructions
     * @param ingredients
     * @param servings
     */
    public Recipe(String id, String name, FoodGroup foodGroup, NutritionDescriptor nutrition,
                  String instructions, List<FoodInstance> ingredients, Double servings) {

        super(id, name, foodGroup, nutrition);
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.servings = servings;
    }

    public Recipe(Food basis, String instructions, List<FoodInstance> ingredients, Double servings) {
        super(basis.getId(), basis.getName(), basis.getFoodGroup(), basis.getNutrition());
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.servings = servings;
    }

    /**
     * Turns the recipe into a FoodInstance
     *
     * @param instanceServings
     * @return
     */
    public FoodInstance createFoodInstance(Double instanceServings) {
        if(instanceServings == null){ return null; }
        Quantity quantity = Quantities.getQuantity(instanceServings * this.nutrition.getServingSize().getValue().doubleValue(), this.nutrition.getServingSize().getUnit());
        return new FoodInstance(id, quantity);
    }

    /**
     * Logs a recipe in the NutritionLog
     *
     * @param instanceServings
     * @return
     */
    public NutritionInstance createNutritionInstance(Double instanceServings) {
        if(instanceServings == null){ return null; }
        String id = Services.ID_SERVICE.getId();
        String foodId = this.id;
        Quantity quantity = Quantities.getQuantity(instanceServings * this.nutrition.getServingSize().getValue().doubleValue(), this.nutrition.getServingSize().getUnit());
        Date consumedAt = new Date();

        return new NutritionInstance(id, foodId, quantity, consumedAt);
    }
}
