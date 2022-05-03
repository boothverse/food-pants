package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.NutritionDescriptor;
import org.junit.After;
import org.junit.jupiter.api.*;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PantryServiceTests {
    static PantryService service = new PantryService();
    static NutritionDescriptor nd = new NutritionDescriptor();
    static Food food = new Food("kdfkjalnad", "Banana", FoodGroup.FRUIT, nd);
    static FoodService foodService = Services.FOOD_SERVICE;
    static Food foodItem1 = new Food("fwjadjda", "Apple", FoodGroup.FRUIT, nd);
    Food foodItem2 = new Food("eqvhjdbj", "Orange", FoodGroup.FRUIT, nd);

    @BeforeAll
    public static void init(){
        service.dao.removeAll();
        service.getItems().stream().forEach(e -> {
            try {
                service.removeItem(e.getId());
            } catch (PantsNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        foodService.addFood(food);
        foodService.addFood(foodItem1);
    }

    @AfterAll
    public static void clear(){
        service.dao.removeAll();
    }

    @Test
    @Order(1)
    public void getItemsEmptyTest(){
        Assertions.assertTrue(service.getItems().isEmpty());
    }

    @Test
    @Order(2)
    public void addItemTest(){
        service.addItem(food.getId(), Quantities.getQuantity(5, Units.KILOGRAM));
        Assertions.assertTrue(service.getItems().get(0).equals(food.createInstance(Quantities.getQuantity(5, Units.KILOGRAM))));

    }

    @Test
    @Order(3)
    public void addItemsTest(){
        List<FoodInstance> instances = new ArrayList<>();
        FoodInstance instance1 = food.createInstance(Quantities.getQuantity(5, Units.KILOGRAM));
        FoodInstance instance1Old = food.createInstance(Quantities.getQuantity(5, Units.KILOGRAM));
        FoodInstance instance1Total = food.createInstance(Quantities.getQuantity(10, Units.KILOGRAM));
        FoodInstance instance2 = foodItem1.createInstance(Quantities.getQuantity(2, Units.KILOGRAM));
        FoodInstance instance3 = foodItem2.createInstance(Quantities.getQuantity(3, Units.KILOGRAM));
        instances.add(instance1);
        instances.add(instance2);
        instances.add(instance3);
        service.addItems(instances);
        Assertions.assertEquals(service.getItems().size(), 3);
        Assertions.assertFalse(service.getItems().contains(instance1Old));
        Assertions.assertTrue(service.getItems().contains(instance1Total));
        Assertions.assertTrue(service.getItems().contains(instance2));
        Assertions.assertTrue(service.getItems().contains(instance3));
    }

    @Test
    @Order(4)
    public void editItemTest(){
        FoodInstance instance1 = food.createInstance(Quantities.getQuantity(5, Units.KILOGRAM));
        FoodInstance instance1Total = food.createInstance(Quantities.getQuantity(10, Units.KILOGRAM));
        try {
            service.editItem(food.getId(), Quantities.getQuantity(5, Units.KILOGRAM));
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
        Assertions.assertTrue(service.getItems().contains(instance1));
        Assertions.assertFalse(service.getItems().contains(instance1Total));
    }

    @Test
    @Order(5)
    public void removeItemWithQuantityTest(){
        FoodInstance instance1 = food.createInstance(Quantities.getQuantity(4, Units.KILOGRAM));
        FoodInstance instance1Old = food.createInstance(Quantities.getQuantity(5, Units.KILOGRAM));
        try {
            service.removeItem(food.getId(), Quantities.getQuantity(1, Units.KILOGRAM));
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
        Assertions.assertTrue(service.getItems().contains(instance1));
        Assertions.assertFalse(service.getItems().contains(instance1Old));

    }

    @Test
    @Order(6)
    public void removeItemTest(){
        try {
            service.removeItem(food.getId());
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
        FoodInstance instance = food.createInstance(Quantities.getQuantity(4, Units.KILOGRAM));
        Assertions.assertFalse(service.getItems().contains(instance));

    }

    @Test
    @Order(7)
    public void getMissingNoMissing(){
        List<FoodInstance> instances = new ArrayList<>();
        FoodInstance instance1 = foodItem1.createInstance(Quantities.getQuantity(2, Units.KILOGRAM));
        FoodInstance instance2 = foodItem2.createInstance(Quantities.getQuantity(3, Units.KILOGRAM));
        instances.add(instance1);
        instances.add(instance2);
        Assertions.assertTrue(service.getMissing(instances).isEmpty());
    }

    @Test
    @Order(8)
    public void getMissingWrongQuantity(){
        List<FoodInstance> instances = new ArrayList<>();
        FoodInstance instance1 = foodItem1.createInstance(Quantities.getQuantity(2, Units.KILOGRAM));
        FoodInstance instance2 = foodItem2.createInstance(Quantities.getQuantity(4, Units.KILOGRAM));
        FoodInstance remaining = foodItem2.createInstance(Quantities.getQuantity(1, Units.KILOGRAM));
        instances.add(instance1);
        instances.add(instance2);
        Assertions.assertTrue(service.getMissing(instances).contains(remaining));
    }

    @Test
    @Order(9)
    public void getMissingOverQuantity(){
        List<FoodInstance> instances = new ArrayList<>();
        FoodInstance instance1 = foodItem1.createInstance(Quantities.getQuantity(1, Units.KILOGRAM));
        FoodInstance instance2 = foodItem2.createInstance(Quantities.getQuantity(2, Units.KILOGRAM));
        instances.add(instance1);
        instances.add(instance2);
        Assertions.assertTrue(service.getMissing(instances).isEmpty());
    }

    @Test
    @Order(10)
    public void containsTrueTest(){
        FoodInstance instance = foodItem1.createInstance(Quantities.getQuantity(2, Units.KILOGRAM));
        Assertions.assertTrue(service.contains(instance));
    }

    @Test
    @Order(11)
    public void containsFalseTest(){
        FoodInstance instance = food.createInstance(Quantities.getQuantity(5, Units.KILOGRAM));
        Assertions.assertFalse(service.contains(instance));
    }

    @Test
    @Order(12)
    public void searchByFoodNameTrueTest(){
        Assertions.assertTrue(service.searchByFoodName("Apple").contains(foodItem1.createInstance(Quantities.getQuantity(2, Units.KILOGRAM))));
    }

    @Test
    @Order(13)
    public void searchByFoodNameFalseTest(){
        Assertions.assertTrue(service.searchByFoodName("Banana").isEmpty());
    }

}
