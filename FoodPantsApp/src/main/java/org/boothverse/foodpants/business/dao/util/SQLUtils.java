package org.boothverse.foodpants.business.dao.util;

public class SQLUtils {
    public static String inQuote(String str) {
        return "'" + str + "'";
    }
}
