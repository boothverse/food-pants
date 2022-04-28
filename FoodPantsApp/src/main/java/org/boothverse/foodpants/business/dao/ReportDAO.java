package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.util.*;
import org.boothverse.foodpants.persistence.ReportPeriod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportDAO extends JDBCListDAO<ReportPeriod> {


    /**
     * Constructor for ReportDAO
     */
    public ReportDAO() { super("REPORTS", new String[]{"id", "startDate", "endDate"}); }

    /**
     * Converts object data to SQL
     *
     * @param data
     * @return
     */
    @Override
    protected String[] objToSQL(ReportPeriod data) {
        return new String[]{
            SQLUtils.inQuote(data.getId()),
            data.getStartDate().getTime() + "",
            data.getEndDate().getTime() + ""
        };
    }

    /**
     * Converts SQL data to object
     *
     * @param rs
     * @return
     * @throws SQLException
     */
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
