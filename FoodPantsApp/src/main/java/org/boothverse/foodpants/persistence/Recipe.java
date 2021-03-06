package org.boothverse.foodpants.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.ui.controllers.FoodController;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import java.util.Date;
import java.util.List;

import static tech.units.indriya.AbstractUnit.ONE;

/**
 * An object representing a recipe
 */
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public class Recipe extends Food {
    private static Logger logger = LogManager.getLogger(Recipe.class);
    private String instructions;
    private List<FoodInstance> ingredients;
    private Double servings;

    /**
     * Creates a new Recipe
     *
     * @param id the id of the recipe
     * @param name the name of the recipe
     * @param foodGroup the food group the recipe falls in
     * @param nutrition the nutrition of the recipe
     * @param instructions instructions associated with the recipe
     * @param ingredients the ingredients associated with the recipe
     * @param servings the number of servings the recipe makes
     */
    public Recipe(String id, String name, FoodGroup foodGroup, NutritionDescriptor nutrition,
                  String instructions, List<FoodInstance> ingredients, Double servings) {

        super(id, name, foodGroup, nutrition);
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.servings = servings;
    }

    /**
     * Creates a new recipe
     *
     * @param basis the food the recipe is based on
     * @param instructions ingredients for preparing the recipe
     * @param ingredients a list of ingredients within the recipe
     * @param servings the number of servings the recipe produces
     */
    public Recipe(Food basis, String instructions, List<FoodInstance> ingredients, Double servings) {
        super(basis.getId(), basis.getName(), basis.getFoodGroup(), basis.getNutrition());
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.servings = servings;
    }

    /**
     * Turns the recipe into a FoodInstance
     *
     * @param instanceServings the number of servings of the recipe produced
     * @return the resulting recipe as a food instance
     */
    public FoodInstance createFoodInstance(Double instanceServings) {
        logger.info("Creating food instance of " + this.getName() + " with id " + this.getId() + " and " + instanceServings + " servings");
        if(instanceServings == null){ return null; }
        Quantity quantity = Quantities.getQuantity(instanceServings * this.nutrition.getServingSize().getValue().doubleValue(), this.nutrition.getServingSize().getUnit());
        return new FoodInstance(id, quantity);
    }

    /**
     * Logs a recipe in the NutritionLog
     *
     * @param instanceServings the number of servings of the recipe the user produces and consumes
     * @return the new nutrition instance
     */
    public NutritionInstance createNutritionInstance(Double instanceServings) {
        logger.info("Creating nutrition instance of " + this.getName() + " with id " + this.getId() + " and " + instanceServings + " servings");
        if(instanceServings == null){ return null; }
        String id = Services.ID_SERVICE.getId();
        String foodId = this.id;
        Quantity quantity = Quantities.getQuantity(instanceServings * this.nutrition.getServingSize().getValue().doubleValue(), this.nutrition.getServingSize().getUnit());
        Date consumedAt = new Date();

        return new NutritionInstance(id, foodId, quantity, consumedAt);
    }
}
