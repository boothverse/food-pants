package org.boothverse.foodpants.business.services.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An exception generated when a specified object or id fails to be found.
 */
public class PantsNotFoundException extends Exception{

    private static Logger logger = LogManager.getLogger(PantsNotFoundException.class);
    /**
     * An exception generated when a specified object or id fails to be found.
     */
    public PantsNotFoundException() {
        super();
        logger.info("New PantsNotFoundException");
    }

    /**
     * An exception generated when a specified object or id fails to be found.
     *
     * @param message a message describing the reason for the exception
     */
    public PantsNotFoundException(String message){
        super(message);
        logger.info("New PantsNotFoundException with message " + message);
    }

}
