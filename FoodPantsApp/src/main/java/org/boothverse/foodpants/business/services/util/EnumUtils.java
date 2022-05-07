package org.boothverse.foodpants.business.services.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class EnumUtils {
    private static Logger logger = LogManager.getLogger(EnumUtils.class);

    /**
     * Return a list of the enum options in an enum.
     *
     * @param e
     * @return
     */
    public static String[] getEnumOptions(Class<? extends Enum<?>> e) {
        logger.info("Getting enums from class " + e.getName() + " as strings");
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

}
