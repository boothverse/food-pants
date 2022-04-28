package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.User;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.util.Map;

public class UserService {

    private final String[] attributes = {"gender", "height", "weight"};
    User current;

    /**
     * Loads the user from the database.
     */
    public UserService() {


    }

    public void register(String name, Map<String, String> info) {
        current = new User();
        current.setName(name);
        current.setGender(info.get(attributes[0]));

        Quantity<Length> height = Quantities.getQuantity(Double.parseDouble((info.get(attributes[1]))), Units.METRE);
        current.setHeight(height);

        Quantity<Mass> weight = Quantities.getQuantity(Double.parseDouble(info.get(attributes[2])), Units.KILOGRAM);
        current.setWeight(weight);

    }
}
