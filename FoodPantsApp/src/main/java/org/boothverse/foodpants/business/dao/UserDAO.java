package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.persistence.User;
import org.boothverse.foodpants.business.dao.util.*;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends JDBCSingleDAO<User> {

    /**
     * Constructs UserDAO
     */
    public UserDAO() {
        super("users", new String[] {"id", "name", "gender", "height", "weight"});
    }

    /**
     * Converts object data to SQL
     *
     * @param data
     * @return
     */
    @Override
    protected String[] objToSQL(User data) {
        return new String[] {
            ID.toString(),
            SQLUtils.inQuote(data.getName()),
            SQLUtils.inQuote(data.getGender()),
            SQLUtils.inQuote(data.getHeight().toString()),
            SQLUtils.inQuote(data.getWeight().toString())
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
    protected User SQLToObj(ResultSet rs) throws SQLException, PantsNotParsedException {
        User data = null;
        if (rs.next()) {
            String name = rs.getString(2);
            String gender = rs.getString(3);
            Quantity<Length> height = (Quantity<Length>) QuantityUtils.parse(rs.getString(4));
            Quantity<Mass> weight = (Quantity<Mass>) QuantityUtils.parse(rs.getString(5));

            data = new User(name, gender, height, weight);
        }
        return data;
    }
}
