package org.boothverse.foodpants.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.measure.Quantity;

public class FoodInstance extends IdObject {
    @Getter @Setter
    private Quantity quantity;

    public FoodInstance(String id, Quantity quantity) {
        super(id);
        this.quantity = quantity;
    }
}
