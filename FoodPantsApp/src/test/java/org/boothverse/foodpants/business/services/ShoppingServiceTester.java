package org.boothverse.foodpants.business.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ShoppingServiceTester {
    ShoppingService service;

    @BeforeEach
    void init() {
        service = new ShoppingService();
    }

    @Test
    void exportTest() {
        assertDoesNotThrow(() -> service.export(Paths.get("C:\\Users\\Public\\shopping-list.pdf")));
    }
}
