package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.util.QuantityParser;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.junit.jupiter.api.*;
import systems.uom.unicode.CLDR;
import tech.units.indriya.format.SimpleUnitFormat;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

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
    void parseQuantitiesTest() throws PantsNotParsedException {
        Unit<?>[] units = new Unit<?>[] {
            CLDR.CALORIE,
            CLDR.OUNCE,
            CLDR.INCH,
            CLDR.FLUID_OUNCE,
            CLDR.POUND,
            CLDR.CUP,
            CLDR.PINT,
            CLDR.GALLON
        };
        Arrays.stream(units).map(Objects::toString).forEach(System.out::println);
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

    @Test
    @Order(3)
    void foodInstanceSaveTest2() {
        Quantity<Volume> quantity = Quantities.getQuantity(30.5, CLDR.CUP);
        FoodInstance item = new FoodInstance(testIds.get(1), quantity);
        pantry.save(item);
    }

    @Test
    @Order(4)
    void foodInstanceLoadTest2() {
        Map<String, FoodInstance> items = pantry.load();
        Quantity<Mass> quantity = Quantities.getQuantity(30.5, CLDR.OUNCE);
        FoodInstance item = new FoodInstance(testIds.get(1), quantity);
        FoodInstance pantryItem = items.get(testIds.get(1));
        assertEquals(item, pantryItem);
    }
}
