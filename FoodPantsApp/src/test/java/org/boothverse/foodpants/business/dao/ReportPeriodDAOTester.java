package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.fileDAO.FileListDAO;
import org.boothverse.foodpants.persistence.ReportPeriod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportPeriodDAOTester {

    protected FileListDAO<ReportPeriod> dao = null;
    protected List<Date> dates;

    @BeforeEach
    void init(){
        dao = new FileListDAO<>(ReportPeriod.class, "ReportPeriods.json");
        dates = Arrays.asList(new Date(1230000L), new Date(4560000L));
    }

    @Test
    @Order(1)
    void reportPeriodSaveTest() throws IOException {
        String id = "0";
        Date startDate = dates.get(0);
        Date endDate = dates.get(1);

        ReportPeriod reportPeriod = new ReportPeriod(id, startDate, endDate);

        List<ReportPeriod> items = new ArrayList<>();
        items.add(reportPeriod);

        dao.save(items);
    }

    @Test
    @Order(2)
    void reportPeriodLoadTest() throws IOException {
        Map<String, ReportPeriod> items = dao.load();
        assertEquals(items.size(), 1);

        ReportPeriod r = (ReportPeriod) items.values().toArray()[0];

        assertEquals(r.getId(), "0");
        assertEquals(r.getStartDate(), dates.get(0));
        assertEquals(r.getEndDate(), dates.get(1));
    }
}
