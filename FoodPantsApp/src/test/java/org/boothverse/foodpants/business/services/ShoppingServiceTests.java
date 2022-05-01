package org.boothverse.foodpants.business.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ShoppingServiceTests {
    ShoppingService service;

    @BeforeEach
    void init() {
        service = new ShoppingService();
    }

    @Test
    void exportTest() {
        assertDoesNotThrow(() -> service.export(Paths.get("shopping-list.pdf")));
    }
}
