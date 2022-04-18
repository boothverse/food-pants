package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.ReportPeriod;
import org.boothverse.foodpants.persistence.User;
import org.junit.jupiter.api.*;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileListDAOTester {

    protected FileListDAO<NutritionInstance> dao = null;

    @BeforeEach
    @Disabled
    void init(){
        dao = new FileListDAO<>(NutritionInstance.class, "NutritionInstance.json");
    }


    @Test
    @Disabled
    @Order(1)
    void nutritionInstanceLoadTest() throws IOException {

        dao = new FileListDAO<>(NutritionInstance.class, "NutritionInstanceLoadTest.json");

        Map<String, NutritionInstance> items = dao.load();
        assertEquals(items.size(), 1);
        NutritionInstance n = (NutritionInstance)items.values().toArray()[0];

        assertEquals(n.getId(), "0");
        assertEquals(n.getFoodId(), "Banana");
        assertEquals(n.getConsumedAt(), new Date());
        assertEquals(n.getQuantity().getUnit(), Units.GRAM);
        assertEquals(n.getQuantity().getValue(), 50);
    }

    @Test
    @Disabled
    @Order(2)
    void nutritionInstanceSingleNonAppendSaveTest() throws IOException {
        FileListDAO<NutritionInstance> dao = new FileListDAO<>(NutritionInstance.class, "NutritionInstance.json");

        String id = "0", foodId = "Banana";
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);
        Date date = new Date();

        NutritionInstance nutritionInstance = new NutritionInstance(id, foodId, quantity, date);

        //dao.save(nutritionInstance, false);
    }

    @Test
    @Disabled
    @Order(3)
    void nutritionInstanceMultipleNonAppendSaveTest() throws IOException {
        FileListDAO<NutritionInstance> dao = new FileListDAO<>(NutritionInstance.class, "NutritionInstance.json");

        String id = "0", foodId = "Banana";
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);
        Date date = new Date();

        NutritionInstance nutritionInstance = new NutritionInstance(id, foodId, quantity, date);

        List<NutritionInstance> items = new ArrayList<>();
        items.add(nutritionInstance);

        dao.save(items, false);
    }

    @Test
    @Disabled
    @Order(4)
    void reportPeriodMultipleNonAppendSaveTest() throws IOException {
        FileListDAO<ReportPeriod> dao = new FileListDAO<>(ReportPeriod.class, "ReportPeriod.json");

        String id = "0";
        Date startDate = new Date(), endDate = new Date();

        ReportPeriod reportPeriod = new ReportPeriod(id, startDate, endDate);

        List<ReportPeriod> items = new ArrayList<>();
        items.add(reportPeriod);

        dao.save(items, false);
    }

    @Test
    @Disabled
    @Order(5)
    void reportPeriodLoadTest() throws IOException {

        FileListDAO<ReportPeriod> dao1 = new FileListDAO<>(ReportPeriod.class, "ReportPeriod.json");

        Map<String, ReportPeriod> items = dao1.load();
        assertEquals(items.size(), 1);
        /*
        NutritionInstance n = (NutritionInstance)items.values().toArray()[0];

        assertEquals(n.getId(), "0");
        assertEquals(n.getFoodId(), "Banana");
        assertEquals(n.getConsumedAt(), new Date());
        assertEquals(n.getQuantity().getUnit(), Units.GRAM);
        assertEquals(n.getQuantity().getValue(), 50);

         */
    }


}
