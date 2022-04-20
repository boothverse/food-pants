package org.boothverse.foodpants.business.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.boothverse.foodpants.business.dao.serialization.QuantityMixin;
import org.boothverse.foodpants.business.dao.serialization.UnitMixin;
import org.boothverse.foodpants.business.dao.util.SQLUtils;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionDescriptor;
import org.boothverse.foodpants.persistence.ReportPeriod;

import javax.measure.Quantity;
import javax.measure.Unit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportDAO extends JDBCListDAO<ReportPeriod> {

        public ReportDAO() { super("reports", new String[]{"id", "startDate", "endDate"}); }

        @Override
        protected String[] objToSQL(ReportPeriod data) {
            return new String[]{
                SQLUtils.inQuote(data.getId()),
                data.getStartDate().getTime() + "",
                data.getEndDate().getTime() + ""
            };
        }

        @Override
        protected Map<String, ReportPeriod> SQLToObj(ResultSet rs) throws SQLException {
            Map<String, ReportPeriod> data = new HashMap<>();

                while (rs.next()) {
                    String id = rs.getString(1);
                    Long startDate = rs.getLong(2);
                    Long endDate = rs.getLong(3);

                    data.put(id, new ReportPeriod(id, new Date(startDate), new Date(endDate)));
                }
            return data;
        }
}
