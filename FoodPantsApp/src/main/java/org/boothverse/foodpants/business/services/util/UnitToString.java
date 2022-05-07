package org.boothverse.foodpants.business.services.util;

import lombok.extern.java.Log;
import org.apache.commons.lang.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.ui.components.QuantitySelector;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;

public class UnitToString {
    private static Logger logger = LogManager.getLogger(UnitToString.class);
    static Unit<?>[] unitClasses = QuantitySelector.unitClasses;
    static String[] quantityOptions = QuantitySelector.quantityOptions;

    public static String convertUnitToString(Unit<?> unit) {
        logger.info("Converting unit " + unit.getName() + " to string");
        int index = ArrayUtils.indexOf(unitClasses, unit);
        if (index != -1) {
            return quantityOptions[index];
        }
        else {
            return unit.getName();
        }
    }

    public static Unit<?> convertStringToUnit(String unit) {
        logger.info("Converting " + unit + " to unit");
        int index = ArrayUtils.indexOf(quantityOptions, unit);
        if (index != -1) {
            logger.info("Unit found: " + unitClasses[index]);
            return unitClasses[index];
        }
        else {
            logger.info("Unit not found. Defaulting to gram");
            return Units.GRAM;
        }

    }
}
