package org.boothverse.foodpants.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;

/**
 * An object representing a physical food
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FoodInstance extends IdObject {
    @Getter @Setter
    protected Quantity<?> quantity;

    /**
     * Creates a new FoodInstance
     *
     * @param id the id of the food instance
     * @param quantity the quantity of the food instance
     */
    public FoodInstance(String id, Quantity<?> quantity) {
        super(id);
        this.quantity = quantity;
    }
}
