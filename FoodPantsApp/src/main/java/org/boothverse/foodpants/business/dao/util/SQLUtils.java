package org.boothverse.foodpants.business.dao.util;

public class SQLUtils {
    /**
     * Puts quotes around a string. Used to represent strings in SQL.
     *
     * @param str
     * @return
     */
    public static String inQuote(String str) {
        return "'" + escapeSingleQuotes(str) + "'";
    }

    protected static String escapeSingleQuotes(String str) {
        return str.replace("'", "''");
    }
}
