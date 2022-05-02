package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.FoodInstance;
import org.junit.jupiter.api.*;
import systems.uom.unicode.CLDR;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Volume;
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
        Quantity<Volume> quantity = Quantities.getQuantity(50, CLDR.CUP);

        FoodInstance foodInstance = new FoodInstance(testIds.get(0), quantity);

        pantry.save(foodInstance);
    }

    @Test
    @Order(2)
    void foodInstanceLoadTest() {
        Quantity<Volume> quantity = Quantities.getQuantity(50, CLDR.CUP);

        Map<String, FoodInstance> foodInstances = pantry.load();
        assertEquals(2, foodInstances.size());

        FoodInstance foodInstance = foodInstances.get(testIds.get(0));

        assertEquals(foodInstance.getQuantity().getUnit(), quantity.getUnit());
        assertEquals(foodInstance.getQuantity().getValue(), quantity.getValue());
    }

    @Test
    @Order(3)
    void foodInstanceSaveTest2() {
        Quantity<Dimensionless> quantity = Quantities.getQuantity(30.5, AbstractUnit.ONE);
        FoodInstance item = new FoodInstance(testIds.get(1), quantity);
        pantry.save(item);
    }

    @Test
    @Order(4)
    void foodInstanceLoadTest2() {
        Map<String, FoodInstance> items = pantry.load();
        Quantity<Dimensionless> quantity = Quantities.getQuantity(30.5, AbstractUnit.ONE);
        FoodInstance item = new FoodInstance(testIds.get(1), quantity);
        FoodInstance pantryItem = items.get(testIds.get(1));
        assertEquals(item, pantryItem);
    }
}
