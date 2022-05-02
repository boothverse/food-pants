package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.dao.SingleDAO;
import org.boothverse.foodpants.business.dao.UserDAO;
import org.boothverse.foodpants.persistence.User;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.MetricPrefix;
import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.util.Map;

public class UserService {

    private final SingleDAO<User> dao = new UserDAO();
    private final String[] attributes = {"gender", "height", "weight"};
    User user;

    /**
     * Loads the user from the database.
     */
    public UserService() {
        user = dao.load();
    }

    /**
     * creates a new user from input
     * Saves new user to database
     *
     * @param name
     * @param info
     * @return
     */
    public User register(String name, Map<String, String> info) {
        user = new User();
        user.setName(name);
        user.setGender(info.get(attributes[0]));

        Quantity<Length> height = Quantities.getQuantity(Double.parseDouble((info.get(attributes[1]))), Units.METRE);
        user.setHeight(height);

        Quantity<Mass> weight = Quantities.getQuantity(Double.parseDouble(info.get(attributes[2])), Units.KILOGRAM);
        user.setWeight(weight);

        dao.save(user);

        return user;
    }

    protected Boolean userIsFemale() {
        return user.getGender().equalsIgnoreCase("female") || user.getGender().equalsIgnoreCase("f");
    }

    protected Double getBodyWeightKg() {
        Quantity<Mass> weight = user.getWeight();
        return weight.getUnit().getConverterTo(Units.KILOGRAM).convert(weight.getValue()).doubleValue();
    }

    protected Double getHeightCm() {
        Quantity<Length> height = user.getHeight();
        return height.getUnit().getConverterTo(MetricPrefix.CENTI(Units.METRE)).convert(height.getValue()).doubleValue();
    }

    protected Integer getAge() {
        // TODO: return the actual age
        return 20;
    }
}
