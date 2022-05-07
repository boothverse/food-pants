package org.boothverse.foodpants.business.services.exceptions;

/**
 * An exception generated during a failed attempt at file exportation.
 */
public class PantsExportShoppingListException extends Exception {

    /**
     * An exception generated during a failed attempt at file exportation.
     */
    public PantsExportShoppingListException() {
        super();
    }

    /**
     *A n exception generated during a failed attempt at file exportation.
     *
     * @param e allows a stack trace to be printed
     */
    public PantsExportShoppingListException(Exception e) {
        super(e);
    }
}
