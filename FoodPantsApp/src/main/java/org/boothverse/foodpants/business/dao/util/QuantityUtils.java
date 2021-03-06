package org.boothverse.foodpants.business.dao.util;

import org.boothverse.foodpants.business.dao.exceptions.PantsNotParsedException;
import org.decimal4j.util.DoubleRounder;
import systems.uom.unicode.CLDR;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;

public class QuantityUtils {

    public static String toString(Quantity<?> quantity) {
        Double value = DoubleRounder.round(quantity.getValue().doubleValue(), 4);
        Unit<?> unit = quantity.getUnit();
        return value + " " + unit;
    }

    public static Quantity<?> parse(String str) throws PantsNotParsedException {
        Number value;
        Unit<?> unit;

        String[] parts = str.split(" ");
        value = Double.parseDouble(parts[0]);
        if (parts.length == 1) {
            unit = AbstractUnit.ONE;
        } else {
            String rawUnit = parts[1];
            unit = CLDR.getInstance().getUnit(rawUnit);
            if (unit == null) {
                unit = Units.getInstance().getUnit(rawUnit);
            }
            if (unit == null) {
                throw new PantsNotParsedException("could not parse the unit " + rawUnit);
            }
        }

        return Quantities.getQuantity(value, unit);
    }
}
