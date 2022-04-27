package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.FoodGroup;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.business.dao.fileDAO.FileListDAO;
import org.boothverse.foodpants.persistence.ReportPeriod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportPeriodDAOTester {

    @Test
    @Order(1)
    void reportPeriodSaveTest() throws IOException {
        ListDAO<ReportPeriod> dao = new ReportDAO();

        Date startDate = new Date(2020, 1, 1) , endDate = new Date(2021, 1, 1);

        ReportPeriod reportPeriod = new ReportPeriod("ukB2Ufeabehiu23buil", startDate, endDate);

        dao.save(reportPeriod);
    }

    @Test
    @Order(2)
    void reportPeriodLoadTest() throws IOException {
        ListDAO<ReportPeriod> dao = new ReportDAO();
        Date startDate = new Date(2020, 1, 1) , endDate = new Date(2021, 1, 1);

        Map<String, ReportPeriod> reportPeriods = dao.load();
        assertEquals(1, reportPeriods.size());

        ReportPeriod reportPeriod = reportPeriods.get("ukB2Ufeabehiu23buil");
        assertEquals(reportPeriod.getStartDate(), startDate);
        assertEquals(reportPeriod.getEndDate(), endDate);
    }
}
