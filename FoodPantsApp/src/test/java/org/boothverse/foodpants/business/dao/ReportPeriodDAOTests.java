package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.Recipe;
import org.boothverse.foodpants.persistence.ReportPeriod;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportPeriodDAOTests {
    static ListDAO<ReportPeriod> dao = new ReportDAO();

    @AfterAll
    static void clear(){
        dao.removeAll();
    }

    @Test
    @Order(1)
    void reportPeriodSaveTest() throws IOException {

        Date startDate = new Date(2020, 1, 1) , endDate = new Date(2021, 1, 1);

        ReportPeriod reportPeriod = new ReportPeriod("ukB2Ufeabehiu23buil", startDate, endDate);

        dao.save(reportPeriod);
    }

    @Test
    @Order(2)
    void reportPeriodLoadTest() throws IOException {
        Date startDate = new Date(2020, 1, 1) , endDate = new Date(2021, 1, 1);

        Map<String, ReportPeriod> reportPeriods = dao.load();
        assertEquals(1, reportPeriods.size());

        ReportPeriod reportPeriod = reportPeriods.get("ukB2Ufeabehiu23buil");
        assertEquals(reportPeriod.getStartDate(), startDate);
        assertEquals(reportPeriod.getEndDate(), endDate);
    }
}
