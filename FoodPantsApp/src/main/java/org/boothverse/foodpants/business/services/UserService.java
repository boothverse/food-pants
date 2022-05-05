package org.boothverse.foodpants.business.services;

import lombok.Getter;
import org.boothverse.foodpants.business.dao.SingleDAO;
import org.boothverse.foodpants.business.dao.UserDAO;
import org.boothverse.foodpants.persistence.User;
import org.joda.time.DateTime;
import org.joda.time.Years;
import tech.units.indriya.unit.Units;

import javax.measure.MetricPrefix;
import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.util.Date;

public class UserService {

    private final SingleDAO<User> dao = new UserDAO();

    @Getter
    private User user;

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
     * @param gender
     * @param height
     * @param weight
     * @param dob
     * @return
     */
    public User register(String name, String gender, Quantity<Length> height, Quantity<Mass> weight, Date dob) {
        user = new User(name, gender, height, weight, dob);
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
        DateTime now = new DateTime(new Date());
        DateTime birthday = new DateTime(user.getDob());

        return Years.yearsBetween(birthday, now).getYears();
    }
}
