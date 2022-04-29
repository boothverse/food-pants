package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodInstanceDAOTester {
    @Test
    @Order(1)
    void foodInstanceSaveTest() throws IOException {
        ListDAO<FoodInstance> dao = new FoodInstanceDAO("FOODINSTANCES");

        String id = "aebfilnjefapin34quv";
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);

        FoodInstance foodInstance = new FoodInstance(id, quantity);

        dao.save(foodInstance);
    }

    @Test
    @Order(2)
    void foodInstanceLoadTest() throws IOException {
        ListDAO<FoodInstance> dao = new FoodInstanceDAO("FOODINSTANCES");
        String id = "aebfilnjefapin34quv";
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);

        Map<String, FoodInstance> foodInstances = dao.load();
        assertEquals(1, foodInstances.size());

        FoodInstance foodInstance = foodInstances.get(id);

        assertEquals(foodInstance.getQuantity().getUnit(), quantity.getUnit());
        assertEquals(foodInstance.getQuantity().getValue(), quantity.getValue());
    }
}
