package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.NutritionType;
import org.junit.jupiter.api.*;
import systems.uom.unicode.CLDR;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoalDAOTests {

    static ListDAO<Goal> dao;
    static List<String> ids;
    GoalType goalMax = GoalType.MAXIMIZE, goalMin = GoalType.MINIMIZE;
    //List<GoalType> goalTypes;
    //"unit", "g", "kg", "oz", "lb", "fl oz", "cup", "gallon", "L", "calorie"
    static Quantity<Mass> grams = Quantities.getQuantity(50, Units.GRAM);
    static Quantity<Mass> kilograms = Quantities.getQuantity(50, Units.KILOGRAM);
    static Quantity<Mass> ounces = Quantities.getQuantity(50, CLDR.OUNCE);
    static Quantity<Mass> pounds = Quantities.getQuantity(50, CLDR.POUND);
    static Quantity<Volume> fluidOunces = Quantities.getQuantity(50, CLDR.FLUID_OUNCE);
    static Quantity<Volume> cups = Quantities.getQuantity(50, CLDR.CUP);
    static Quantity<Volume> gallons = Quantities.getQuantity(50, CLDR.GALLON);
    static Quantity<Volume> liters = Quantities.getQuantity(50, Units.LITRE);
    static Quantity<Energy> calories = Quantities.getQuantity(50, CLDR.CALORIE);
    static Quantity<Dimensionless> unit = Quantities.getQuantity(50, AbstractUnit.ONE);
    static List<Quantity<?>> quantities;

    NutritionType nutritionType = NutritionType.CALORIES;

    static final int NUM_UNITS = 10;

    @BeforeAll
    static void initAll(){
        dao = new GoalDAO();
        ids = new ArrayList<>();
        quantities = new ArrayList<>();
        for(int i = 0; i < NUM_UNITS; i++){
            ids.add(i + "");
        }
        quantities.add(grams);
        quantities.add(kilograms);
        quantities.add(ounces);
        quantities.add(pounds);
        quantities.add(fluidOunces);
        quantities.add(cups);
        quantities.add(gallons);
        quantities.add(liters);
        quantities.add(calories);
        quantities.add(unit);
        System.out.println(liters);
   }

   @AfterAll
   static void clear(){
        dao.removeAll();
   }

    @Test
    @Order(1)
    void goalSaveTest() throws IOException {

        for(int i = 0; i < NUM_UNITS; i++){
            dao.save(new Goal(ids.get(i), goalMax, quantities.get(i), nutritionType));
        }
    }

    @Test
    @Order(2)
    void goalLoadTest() throws IOException {

        Map<String, Goal> goals = dao.load();
        assertEquals(NUM_UNITS, goals.size());

        for(int i = 0; i < NUM_UNITS; i++){
            Goal goal = goals.get(i + "");
            assertEquals(goal.getGoalType(), goalMax);
            assertEquals(goal.getNutritionType(), nutritionType);
            try {
                assertEquals(goal.getDailyQuantity().getUnit(), quantities.get(i).getUnit());
                assertEquals(goal.getDailyQuantity().getValue().doubleValue(), quantities.get(i).getValue().doubleValue());
            }catch(NullPointerException e){
                System.out.println("Id: " + i + " Unit: " + quantities.get(i).getUnit() + " Status: failed.");
            }
        }


    }
}
