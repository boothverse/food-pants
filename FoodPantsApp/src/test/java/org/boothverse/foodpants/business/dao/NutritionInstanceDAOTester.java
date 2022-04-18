package org.boothverse.foodpants.business.dao;

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

    protected FileListDAO<NutritionInstance> dao = null;

    @BeforeEach
    void init(){
        dao = new FileListDAO<>(NutritionInstance.class, "NutritionInstances.json");
    }

    @Test
    @Order(1)
    void nutritionInstanceSaveTest() throws IOException {
        String id = "0", foodId = "Banana";
        Quantity<Mass> quantity = Quantities.getQuantity(50, Units.GRAM);
        Date date = new Date();

        NutritionInstance nutritionInstance = new NutritionInstance(id, foodId, quantity, date);

        List<NutritionInstance> items = new ArrayList<>();
        items.add(nutritionInstance);
        items.add(nutritionInstance);

        dao.save(items);
    }

    @Test
    @Order(2)
    void nutritionInstanceLoadTest() throws IOException {
        Map<String, NutritionInstance> items = dao.load();
        assertEquals(items.size(), 1);
        NutritionInstance n = (NutritionInstance) items.values().toArray()[0];

        assertEquals(n.getId(), "0");
        assertEquals(n.getFoodId(), "Banana");
        assertEquals(n.getConsumedAt(), new Date());
        assertEquals(n.getQuantity().getUnit(), Units.GRAM);
        assertEquals(n.getQuantity().getValue(), 50);
    }
}
