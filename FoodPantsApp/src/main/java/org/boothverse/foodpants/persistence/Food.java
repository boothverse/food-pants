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
        logger.info("Creating instance of food with id " + id + " and quantity " + quantity);
        return new FoodInstance(id, quantity);
    }
}
