package org.boothverse.foodpants.business.services.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An exception generated during a failed attempt at file exportation.
 */
public class PantsExportShoppingListException extends Exception {

    private static Logger logger = LogManager.getLogger(PantsExportShoppingListException.class);

    /**
     * An exception generated during a failed attempt at file exportation.
     */
    public PantsExportShoppingListException() {
        super();
        logger.info("New PantsExportShoppingListException");
    }

    /**
     * An exception generated during a failed attempt at file exportation.
     *
     * @param e allows a stack trace to be printed
     */
    public PantsExportShoppingListException(Exception e) {
        super(e);
        logger.info("New PantsExportShoppingListException with sub-exception " + e.getMessage());
    }
}
