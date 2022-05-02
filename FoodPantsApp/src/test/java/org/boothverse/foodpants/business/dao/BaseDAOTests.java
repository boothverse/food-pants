package org.boothverse.foodpants.business.dao;

import org.boothverse.foodpants.business.services.IdService;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.persistence.FoodInstance;
import org.boothverse.foodpants.persistence.IdObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class BaseDAOTests {

    static final int NUM_TEST_IDS = 10;

    static List<String> testIds;
    static IdService idService;

    static List<?> backup;
    static ListDAO<?> dao;

    static ListDAO<?> createDao() {
        return null;
    }

    @BeforeAll
    static void setup() {
        // Clear table
        dao = createDao();
        dao = new FoodInstanceDAO("pantry");
        backup = new ArrayList<>(dao.load().values());
        dao.removeAll();

        initIds();
    }


    static void initIds() {
        idService = Services.ID_SERVICE;
        testIds = new ArrayList<>();
        for (int i = 0; i < NUM_TEST_IDS; i++) {
            testIds.add(idService.getId());
        }
    }

}
