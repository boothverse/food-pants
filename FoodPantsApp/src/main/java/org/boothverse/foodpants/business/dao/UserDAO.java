package org.boothverse.foodpants.business.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.persistence.User;
import org.boothverse.foodpants.business.dao.util.*;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserDAO extends JDBCSingleDAO<User> {

    private static Logger logger = LogManager.getLogger(UserDAO.class);

    /**
     * Constructs UserDAO
     */
    public UserDAO() {
        super("users", new String[] {"id", "name", "gender", "height", "weight", "dob"});
    }

    /**
     * Converts object data to SQL
     *
     * @param data
     * @return
     */
    @Override
    protected String[] objToSQL(User data) {
        logger.info("user " + data.getName() + " converted to string");
        return new String[] {
            ID.toString(),
            SQLUtils.inQuote(data.getName()),
            SQLUtils.inQuote(data.getGender()),
            SQLUtils.inQuote(QuantityUtils.toString(data.getHeight())),
            SQLUtils.inQuote(QuantityUtils.toString(data.getWeight())),
            data.getDob().getTime() + ""
        };
    }

    /**
     * Converts SQL to object data
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    protected User SQLToObj(ResultSet rs) throws SQLException {
        User data = null;
        if (rs.next()) {
            String name = rs.getString(2);
            String gender = rs.getString(3);

            Quantity<Length> height;
            try {
                height = (Quantity<Length>) QuantityUtils.parse(rs.getString(4));
                logger.info("height successfully converted to Quantity UserDAO");
            } catch (PantsNotParsedException e) {
                height = null;
                logger.info("height convert to quantity unsuccessful UserDAO");
            }

            Quantity<Mass> weight;
            try {
                weight = (Quantity<Mass>) QuantityUtils.parse(rs.getString(5));
                logger.info("weight successfully converted to Quantity UserDAO");
            } catch (PantsNotParsedException e) {
                weight = null;
                logger.info("weight convert to Quantity unsuccessful UserDAO");
            }

            Date dob;
            try {
                dob = new Date(rs.getLong(6));
                logger.info("dob successfully created UserDAO");
            } catch (Exception e) {
                dob = null;
                logger.info("dob conversion not successful");
            }

            data = new User(name, gender, height, weight, dob);
            logger.info("new user created with name " + data.getName());
        }
        return data;
    }
}
