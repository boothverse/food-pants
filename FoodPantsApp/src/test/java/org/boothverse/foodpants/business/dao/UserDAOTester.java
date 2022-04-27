package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDAOTester {

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
