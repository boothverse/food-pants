package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.dao.FoodDAO;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionDescriptor;
import org.junit.jupiter.api.*;

public class IdServiceTests {
    IdService service = new IdService();
    FoodService foodService = new FoodService();
    @Test
    public void getIdTest(){
        String id = service.getId();
        Assertions.assertEquals(id.length(), 20);
        Assertions.assertThrows(PantsNotFoundException.class, () -> {foodService.getFood(id);});

    }
}
