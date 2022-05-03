package org.boothverse.foodpants.business.services.util;

import org.apache.commons.lang.ArrayUtils;
import org.boothverse.foodpants.ui.components.QuantitySelector;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;

public class UnitToString {
    static Unit<?>[] unitClasses = QuantitySelector.unitClasses;
    static String[] quantityOptions = QuantitySelector.quantityOptions;

    public static String convertUnitToString(Unit<?> unit) {
        int index = ArrayUtils.indexOf(unitClasses, unit);
        if (index != -1) {
            return quantityOptions[index];
        }
        else {
            return unit.getName();
        }
    }

    public static Unit<?> convertStringToUnit(String unit) {
        int index = ArrayUtils.indexOf(quantityOptions, unit);
        if (index != -1) {
            return unitClasses[index];
        }
        else {
            return Units.GRAM;
        }

    }
}
