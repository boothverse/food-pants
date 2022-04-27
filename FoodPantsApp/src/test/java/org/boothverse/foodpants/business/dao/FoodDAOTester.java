package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionDescriptor;
import org.boothverse.foodpants.persistence.NutritionType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tec.units.ri.AbstractQuantity.ONE;

public class FoodDAOTester {

    @Test
    @Order(1)
    void foodSaveTest() {
        ListDAO<Food> dao = new FoodDAO();

        NutritionDescriptor nutrition = new NutritionDescriptor();
        nutrition.setNutritionInfo(Map.of(
            NutritionType.CALORIES, Quantities.getQuantity(105, Units.KILOGRAM)));
        nutrition.setServingSize(ONE);

        Food food = new Food("ja2f039jawoiefj029JF", "Banana", FoodGroup.FRUIT, nutrition);

        dao.save(food);
    }

    @Test
    @Order(2)
    void foodLoadTest() {
        ListDAO<Food> dao = new FoodDAO();

        Map<String, Food> foods = dao.load();
        assertEquals(1, foods.size());

        Food food = foods.get("ja2f039jawoiefj029JF");
        assertEquals(food.getName(), "Banana");
        assertEquals(food.getFoodGroup(), FoodGroup.FRUIT);
    }

}
