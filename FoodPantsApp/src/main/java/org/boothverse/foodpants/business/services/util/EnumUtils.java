package org.boothverse.foodpants.business.services.util;

import java.util.Arrays;

public class EnumUtils {

    /**
     * Return a list of the enum options in an enum.
     *
     * @param e
     * @return
     */
    public static String[] getEnumOptions(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

}
