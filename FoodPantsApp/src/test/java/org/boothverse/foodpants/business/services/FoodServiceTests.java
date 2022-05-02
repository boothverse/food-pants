package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionDescriptor;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FoodServiceTests {
    FoodService service = new FoodService();
    NutritionDescriptor nd = new NutritionDescriptor();
    Food food = new Food("sfkjsfks", "Banana", FoodGroup.FRUIT, nd);
    Food foodEdited = new Food("sfkjsfks", "Apple", FoodGroup.FRUIT, nd);
    @Test
    @Order(1)
    public void addFoodTest(){
        service.addFood(food);
        Assertions.assertTrue(service.getFoods().contains(food));
    }

    @Test
    @Order(2)
    public void getFoodTest(){
        try {
            Assertions.assertTrue(service.getFood("sfkjsfks").equals(food));
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(3)
    public void editFoodTest(){
        try {
            service.editFood(foodEdited);
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
        Assertions.assertFalse(service.getFoods().contains(food));
        Assertions.assertTrue(service.getFoods().contains(foodEdited));
    }

    @Test
    @Order(4)
    public void getFoodNameTest() throws PantsNotFoundException {
        Assertions.assertTrue(service.getFoodName("sfkjsfks").equals(foodEdited.getName()));
    }

    @Test
    @Order(5)
    public void getFoodsTest(){
        Assertions.assertTrue(service.getFoods().contains(foodEdited));
    }

    @Test
    @Order(6)
    public void removeFoodTest() throws PantsNotFoundException {
        service.removeFood("sfkjsfks");
        Assertions.assertFalse(service.getFoods().contains(foodEdited));
    }
}
