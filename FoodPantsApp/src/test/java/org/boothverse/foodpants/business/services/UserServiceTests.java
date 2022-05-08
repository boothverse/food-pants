package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.util.QuantityUtils;
import org.junit.jupiter.api.*;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTests {
    UserService service = new UserService();
    Map<String, Object> info = new HashMap<>();

    @Test
    @Order(1)
    public void registerMaleTest() throws PantsNotParsedException {
        service.register("Pat", "Male", (Quantity<Length>) QuantityUtils.parse("6 m"), (Quantity<Mass>) QuantityUtils.parse("175 lb"), new Date());
        info.clear();
    }

    @Test
    @Order(2)
    public void userIsFemaleFalseTest(){
        Assertions.assertFalse(service.userIsFemale());
    }

    @Test
    @Order(3)
    public void overrideRegisterTest() throws PantsNotParsedException {
        service.register("Patricia", "f", (Quantity<Length>) QuantityUtils.parse("1.54 m"), (Quantity<Mass>) QuantityUtils.parse("6000 g"), new Date());
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
        Assertions.assertEquals(service.getBodyWeightKg(), 6);
    }

    @Test
    @Order(6)
    public void getHeightCmTest(){
        Assertions.assertEquals(service.getHeightCm(), 154);
    }

    //GET AGE TEST
}
