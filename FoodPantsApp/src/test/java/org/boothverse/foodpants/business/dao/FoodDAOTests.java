package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionDescriptor;
import org.boothverse.foodpants.persistence.NutritionType;
import org.junit.jupiter.api.*;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.units.indriya.AbstractQuantity.ONE;

public class FoodDAOTests extends BaseDAOTests {

    static ListDAO<Food> dao;

    @BeforeAll
    static void setup() {
        dao = new FoodDAO();
        setup(dao);
    }

    @AfterAll
    static void clear(){
        dao.removeAll();
    }

    @Test
    @Order(1)
    void foodSaveTest() {
        NutritionDescriptor nutrition = new NutritionDescriptor();
        nutrition.setNutritionInfo(Map.of(
            NutritionType.CALORIES, Quantities.getQuantity(105, Units.KILOGRAM)));
        nutrition.setServingSize(ONE);

        Food food = new Food(testIds.get(0), "Banana", FoodGroup.FRUIT, nutrition);

        dao.save(food);
    }

    @Test
    @Order(2)
    void foodLoadTest() {
        Map<String, Food> foods = dao.load();
        assertEquals(1, foods.size());

        Food food = foods.get(testIds.get(0));
        assertEquals(food.getName(), "Banana");
        assertEquals(food.getFoodGroup(), FoodGroup.FRUIT);
    }

    @Test
    @Order(3)
    void foodSaveTest2() {
        NutritionDescriptor nutrition = new NutritionDescriptor();
        nutrition.setNutritionInfo(Map.of(
            NutritionType.CALORIES, Quantities.getQuantity(105, Units.KILOGRAM)));
        nutrition.setServingSize(ONE);

        Food food = new Food(testIds.get(0), "Apple", FoodGroup.FRUIT, nutrition);

        dao.save(food);
    }

    @Test
    @Order(4)
    void foodLoadTest2() {
        Map<String, Food> foods = dao.load();
        assertEquals(1, foods.size());

        Food food = foods.get(testIds.get(0));
        assertEquals(food.getName(), "Apple");
        assertEquals(food.getFoodGroup(), FoodGroup.FRUIT);
    }

}
