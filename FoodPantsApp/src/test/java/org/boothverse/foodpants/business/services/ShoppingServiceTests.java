package org.boothverse.foodpants.business.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ShoppingServiceTests {
    static ShoppingService service;

    @BeforeAll
    static void init() {
        service = new ShoppingService();
    }

    @AfterAll
    static void clear(){
        service.dao.removeAll();
    }


    @Test
    void exportTest() {
        assertDoesNotThrow(() -> service.export(Paths.get("shopping-list.pdf")));
    }
}
