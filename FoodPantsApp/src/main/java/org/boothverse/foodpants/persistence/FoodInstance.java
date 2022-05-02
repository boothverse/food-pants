package org.boothverse.foodpants.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FoodInstance extends IdObject {
    @Getter @Setter
    protected Quantity<?> quantity;

    /**
     * Creates a new FoodInstance
     *
     * @param id
     * @param quantity
     */
    public FoodInstance(String id, Quantity<?> quantity) {
        super(id);
        this.quantity = quantity;
    }
}
