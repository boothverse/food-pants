package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.persistence.User;
import org.boothverse.foodpants.business.dao.util.*;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Manages connecting and interacting with the user database
 */
public class UserDAO extends JDBCSingleDAO<User> {

    /**
     * Constructs UserDAO
     */
    public UserDAO() {
        super("users", new String[] {"id", "name", "gender", "height", "weight", "dob"});
    }

    @Override
    protected String[] objToSQL(User data) {
        return new String[] {
            ID.toString(),
            SQLUtils.inQuote(data.getName()),
            SQLUtils.inQuote(data.getGender()),
            SQLUtils.inQuote(QuantityUtils.toString(data.getHeight())),
            SQLUtils.inQuote(QuantityUtils.toString(data.getWeight())),
            data.getDob().getTime() + ""
        };
    }

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
            } catch (PantsNotParsedException e) {
                height = null;
            }

            Quantity<Mass> weight;
            try {
                weight = (Quantity<Mass>) QuantityUtils.parse(rs.getString(5));
            } catch (PantsNotParsedException e) {
                weight = null;
            }

            Date dob;
            try {
                dob = new Date(rs.getLong(6));
            } catch (Exception e) {
                dob = null;
            }

            data = new User(name, gender, height, weight, dob);
        }
        return data;
    }
}
