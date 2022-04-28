package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.dao.fileDAO.FileListDAO;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.junit.jupiter.api.*;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NutritionInstanceDAOTester {

    @Test
    @Order(1)
    void nutritionInstanceSaveTest() throws IOException {
        ListDAO<NutritionInstance> dao = new NutritionInstanceDAO();

        String id = "aebfilnjefapin34quv", foodId = "Banana";
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);
        Date date = new Date(2020, 1, 1);

        NutritionInstance nutritionInstance = new NutritionInstance(id, foodId, quantity, date);

        dao.save(nutritionInstance);
    }

    @Test
    @Order(2)
    void nutritionInstanceLoadTest() throws IOException {
        ListDAO<NutritionInstance> dao = new NutritionInstanceDAO();
        String id = "aebfilnjefapin34quv", foodId = "Banana";
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);
        Date date = new Date(2020, 1, 1);

        Map<String, NutritionInstance> nutritionInstances = dao.load();
        assertEquals(1, nutritionInstances.size());

        NutritionInstance nutritionInstance = nutritionInstances.get(id);

        assertEquals(nutritionInstance.getFoodId(), foodId);
        assertEquals(nutritionInstance.getConsumedAt(), date);
        assertEquals(nutritionInstance.getQuantity().getUnit(), quantity.getUnit());
        assertEquals(nutritionInstance.getQuantity().getValue(), 50.0);
    }
}
