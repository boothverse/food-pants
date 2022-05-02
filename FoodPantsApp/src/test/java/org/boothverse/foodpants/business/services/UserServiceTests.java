package org.boothverse.foodpants.business.services;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTests {
    UserService service = new UserService();
    Map<String, String> info = new HashMap<>();

    @Test
    @Order(1)
    public void registerMaleTest(){
        info.put("gender", "Male");
        info.put("height", "3");
        info.put("weight", "5");
        service.register("Pat", info);
        info.clear();
    }

    @Test
    @Order(2)
    public void userIsFemaleFalseTest(){
        Assertions.assertFalse(service.userIsFemale());
    }

    @Test
    @Order(3)
    public void overrideRegisterTest(){
        info.put("gender", "f");
        info.put("height", "3");
        info.put("weight", "5");
        service.register("Patricia", info);
        info.clear();
    }

    @Test
    @Order(4)
    public void userIsFemaleTrueTest(){
        Assertions.assertTrue(service.userIsFemale());
    }

    @Test
    @Order(5)
    public void getBodyWeightKgTest(){
        Assertions.assertEquals(service.getBodyWeightKg(), 5);
    }

    @Test
    @Order(6)
    public void getHeightCmTest(){
        Assertions.assertEquals(service.getHeightCm(), 300);
    }
}
