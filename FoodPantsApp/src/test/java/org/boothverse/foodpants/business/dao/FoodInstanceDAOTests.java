package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.util.JDBCUtils;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.junit.jupiter.api.*;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodInstanceDAOTests extends BaseDAOTests {

    static ListDAO<FoodInstance> pantry;

    @BeforeAll
    static void setup() {
        pantry = new FoodInstanceDAO("pantry");
        setup(pantry);
        executeScript("pantry_01.sql");
    }

    @Test
    @Order(1)
    void foodInstanceSaveTest() {
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);

        FoodInstance foodInstance = new FoodInstance(testIds.get(0), quantity);

        pantry.save(foodInstance);
    }

    @Test
    @Order(2)
    void foodInstanceLoadTest() {
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);

        Map<String, FoodInstance> foodInstances = pantry.load();
        assertEquals(2, foodInstances.size());

        FoodInstance foodInstance = foodInstances.get(testIds.get(0));

        assertEquals(foodInstance.getQuantity().getUnit(), quantity.getUnit());
        assertEquals(foodInstance.getQuantity().getValue(), quantity.getValue());
    }
}
