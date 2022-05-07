package org.boothverse.foodpants.business.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.util.*;
import org.boothverse.foodpants.persistence.ReportPeriod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages connecting and interacting with the report database
 */
public class ReportDAO extends JDBCListDAO<ReportPeriod> {

    private static Logger logger = LogManager.getLogger(ReportDAO.class);

    /**
     * Constructor for ReportDAO
     */
    public ReportDAO() { super("REPORTS", new String[]{"id", "startDate", "endDate"}); }

    @Override
    protected String[] objToSQL(ReportPeriod data) {
        logger.debug(data.getId() + " converted to SQL format");
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
                logger.debug("report " + id + " converted from SQL, added to map");
            }
        return data;
    }
}
