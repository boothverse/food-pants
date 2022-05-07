package org.boothverse.foodpants.business.services;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

/**
 * service dealing with processing users
 */
public class UserService {
    private static Logger logger = LogManager.getLogger(UserService.class);
    private final SingleDAO<User> dao = new UserDAO();

    @Getter
    private User user;

    /**
     * Loads the user from the database.
     */
    public UserService() {
        logger.info("Loading user from database");
        user = dao.load();
    }

    /**
     * creates a new user from input
     * Saves new user to database
     *
     * @param name the name of the user
     * @param gender the gender of the user
     * @param height the height of the user
     * @param weight the weight of the user
     * @param dob the date of birth of the user
     */
    public void register(String name, String gender, Quantity<Length> height, Quantity<Mass> weight, Date dob) {
        logger.info("Registering new user with name " + name + ", gender " + gender + ", height " + height + ", weight " + weight + ", and date of birth " + dob);
        user = new User(name, gender, height, weight, dob);
        logger.info("Saving new user with name " + user.getName() + " to database");
        dao.save(user);
    }

    protected Boolean userIsFemale() {
        logger.info("Determining whether user with gender " + user.getGender() + " is female");
        return user.getGender().equalsIgnoreCase("female") || user.getGender().equalsIgnoreCase("f");
    }

    protected Double getBodyWeightKg() {
        Quantity<Mass> weight = user.getWeight();
        logger.info("Getting user weight (" + weight + ") in kg");
        return weight.getUnit().getConverterTo(Units.KILOGRAM).convert(weight.getValue()).doubleValue();
    }

    protected Double getHeightCm() {
        Quantity<Length> height = user.getHeight();
        logger.info("Getting user height (" + height + ") in cm");
        return height.getUnit().getConverterTo(MetricPrefix.CENTI(Units.METRE)).convert(height.getValue()).doubleValue();
    }

    protected Integer getAge() {
        DateTime now = new DateTime(new Date());
        DateTime birthday = new DateTime(user.getDob());
        logger.info("Getting user age from date of birth (" + user.getDob() + ")");
        return Years.yearsBetween(birthday, now).getYears();
    }
}
