package org.boothverse.foodpants.persistence;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.measure.Quantity;

//@JsonTypeName("foodinstance")
@NoArgsConstructor
public class FoodInstance extends IdObject {
    @Getter @Setter
    private Quantity quantity;

    public FoodInstance(String id, Quantity quantity) {
        super(id);
        this.quantity = quantity;
    }
}
