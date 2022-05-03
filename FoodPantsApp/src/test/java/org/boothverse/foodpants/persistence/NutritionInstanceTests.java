package org.boothverse.foodpants.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.time.Instant;
import java.util.Date;

public class NutritionInstanceTests {
    Quantity<Mass> quantity = Quantities.getQuantity(5, Units.KILOGRAM);
    Date consumedAt = Date.from(Instant.now());
    NutritionInstance nullFields = new NutritionInstance();
    NutritionInstance nutritionInstance = new NutritionInstance("1", "banana", quantity, consumedAt);
    @Test
    public void checkToString(){
        Assertions.assertEquals(nutritionInstance.toString(), "NutritionInstance(id=1, quantity=5 kg, foodId=banana, consumedAt=" + consumedAt.toString() +")");
    }

    @Test
    public void checkToStringWithNullFields(){
        Assertions.assertEquals(nullFields.toString(), "NutritionInstance(id=null, quantity=null, foodId=null, consumedAt=null)");
    }
}
