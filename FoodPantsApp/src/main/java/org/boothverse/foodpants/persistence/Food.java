package org.boothverse.foodpants.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.ui.controllers.FoodController;

import javax.measure.Quantity;
import java.util.Objects;

/**
 * An object representing the conceptual idea of a specific food
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Food extends IdObject {
    private static Logger logger = LogManager.getLogger(Food.class);
    @Getter
    protected String name;
    @Getter
    protected FoodGroup foodGroup;
    @Getter @Setter
    protected NutritionDescriptor nutrition;

    /**
     * Creates a new Food
     *
     * @param id the id of the food
     * @param name the name of the food
     * @param foodGroup the food group which the food falls under
     * @param nutrition the nutrition information  of the food
     */
    public Food(String id, String name, FoodGroup foodGroup, NutritionDescriptor nutrition) {
        super(id);
        this.name = name;
        this.foodGroup = foodGroup;
        this.nutrition = nutrition;
    }

    /**
     * Creates a food given a quantity
     *
     * @param quantity the given quantity
     * @return the resulting food
     */
    public FoodInstance createInstance(Quantity<?> quantity) {
        logger.info("Creating instance of food with id " + id + " and quantity " + quantity);
        return new FoodInstance(id, quantity);
    }
}
