package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;

@NoArgsConstructor
public class FoodInstance extends IdObject {
    @Getter @Setter
    protected Quantity<?> quantity;

    public FoodInstance(String id, Quantity<?> quantity) {
        super(id);
        this.quantity = quantity;
    }
}
