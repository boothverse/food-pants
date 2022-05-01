package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTests {

    @Test
    @Order(1)
    void userSaveTest() {
        SingleDAO<User> dao = new UserDAO();

        String name = "Gandhi";
        String gender = "M";
        Quantity<Length> height = Quantities.getQuantity(1.65, Units.METRE);
        Quantity<Mass> weight = Quantities.getQuantity(60, Units.KILOGRAM);

        User user = new User(name, gender, height, weight);

        dao.save(user);
    }

    @Test
    @Order(2)
    void userLoadTest() {
        SingleDAO<User> dao = new UserDAO();

        User user = dao.load();

        assertEquals(user.getName(), "Gandhi");
        assertEquals(user.getHeight().getUnit(), Units.METRE);
    }

}
