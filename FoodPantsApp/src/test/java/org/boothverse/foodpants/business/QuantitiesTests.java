package org.boothverse.foodpants.business;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.boothverse.foodpants.business.dao.util.QuantityUtils;
import org.junit.jupiter.api.Test;
import systems.uom.unicode.CLDR;

import javax.measure.MetricPrefix;
import javax.measure.Quantity;
import javax.measure.quantity.Length;

public class QuantitiesTests {

    @Test
    void bestTest() throws PantsNotParsedException {
        Quantity<Length> q = (Quantity<Length>) QuantityUtils.parse("10 kg");
        System.out.println(q.getUnit().getDimension().equals(MetricPrefix.KILO(CLDR.GRAM).getDimension()));
    }
}
