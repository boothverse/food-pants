package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.*;
import org.junit.jupiter.api.*;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.quantity.time.TemporalQuantity;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NutritionServiceTests {
    static NutritionService service;
    static Quantity<Mass> quantity;
    static Date consumedAt;
    static NutritionInstance nullFields;
    static NutritionInstance nutritionInstance;
    static NutritionInstance nutritionInstanceEdited;
    static Goal<Mass> goal;
    static Goal<Mass> editedGoal;
    static ReportPeriod reportPeriod;
    static ReportPeriod editedReportPeriod;

    @BeforeAll
    public static void init(){
        service = new NutritionService();
        quantity = Quantities.getQuantity(5, Units.KILOGRAM);
        consumedAt = Date.from(Instant.now());
        nullFields = new NutritionInstance();
        nutritionInstance = new NutritionInstance("dajkkjad", "banana", quantity, consumedAt);
        nutritionInstanceEdited = new NutritionInstance("dajkkjad", "apple", quantity, consumedAt);
        goal = new Goal<>("adsbjkadbnk", GoalType.MAXIMIZE, quantity, NutritionType.CALORIES);
        editedGoal = new Goal<>("adsbjkadbnk", GoalType.MINIMIZE, quantity, NutritionType.SODIUM);
        reportPeriod = new ReportPeriod("wtohjbnahb", Date.from(Instant.now()), Date.from(Instant.now()));
        editedReportPeriod = new ReportPeriod("wtohjbnahb", Date.from(Instant.now()), Date.from(Instant.now().plus(5, ChronoUnit.DAYS)));
    }

    @Test
    @Order(1)
    public void addItemTest(){
        service.addItem(nutritionInstance);
        Assertions.assertTrue(service.items.containsKey(nutritionInstance.getId()));
    }

    @Test
    @Order(2)
    public void getItemTest(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);
        tomorrow.set(Calendar.HOUR_OF_DAY, 0);
        Date begin = today.getTime();
        Date end = tomorrow.getTime();
        Assertions.assertTrue(service.getItems(begin, end).get(0).equals(nutritionInstance));

    }

    @Test
    @Order(3)
    public void editItemTest(){
        try {
            service.editItem(nutritionInstanceEdited);
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
        Assertions.assertTrue(service.items.values().contains(nutritionInstanceEdited));
        Assertions.assertFalse(service.items.values().contains(nutritionInstance));
    }

    @Test
    @Order(4)
    public void removeItemTest() throws PantsNotFoundException {
        service.removeItem("dajkkjad");
        Assertions.assertFalse(service.items.containsKey(nutritionInstanceEdited.getFoodId()));
    }

    @Test
    @Order(5)
    public void addGoalTest(){
        service.addGoal(goal);
        Assertions.assertTrue(service.goals.containsKey(goal.getId()));
    }

    @Test
    @Order(6)
    public void editGoalTest(){
        try {
            service.editGoal(editedGoal);
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
        Assertions.assertTrue(service.goals.values().contains(editedGoal));
        Assertions.assertFalse(service.goals.values().contains(goal));
    }

    @Test
    @Order(7)
    public void removeGoalTest() throws PantsNotFoundException {
        service.removeGoal("adsbjkadbnk");
        Assertions.assertFalse(service.goals.containsKey(editedGoal.getId()));
    }

    @Test
    @Order(8)
    public void addReportPeriodTest(){
        service.addReport(reportPeriod);
        Assertions.assertTrue(service.reportPeriods.containsKey(reportPeriod.getId()));
    }

    @Test
    @Order(9)
    public void editReportPeriodTest(){
        try {
            service.editReport(editedReportPeriod);
        } catch (PantsNotFoundException e) {
            Assertions.fail();
        }
        Assertions.assertTrue(service.reportPeriods.values().contains(editedReportPeriod));
        Assertions.assertFalse(service.reportPeriods.values().contains(reportPeriod));
    }

    @Test
    @Order(10)
    public void removeReportPeriodTest() throws PantsNotFoundException {
        service.removeReport("wtohjbnahb");
        Assertions.assertFalse(service.reportPeriods.containsKey(editedReportPeriod.getId()));
    }
}
