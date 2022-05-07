package org.boothverse.foodpants.business.dao.exceptions;

/**
 * An exception generated when parsing data fails.
 */
public class PantsNotParsedException extends Exception {

    /**
     * An exception generated when parsing data fails.
     */
    public PantsNotParsedException() {
        super();
    }

    /**
     * An exception generated when parsing data fails.
     *
     * @param message description of exception cause
     */
    public PantsNotParsedException(String message) {
        super(message);
    }
}
