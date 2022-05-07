package org.boothverse.foodpants.business.services.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An exception generated when a unit conversion fails.
 */
public class PantsConversionFailedException extends Exception{

    private static Logger logger = LogManager.getLogger(PantsConversionFailedException.class);
    /**
     * An exception generated when a unit conversion fails.
     */
    public PantsConversionFailedException() {
        super();
        logger.info("New PantsConversionFailedException");
    }

    /**
     * An exception generated when a unit conversion fails.
     *
     * @param message a message describing the reason for the exception
     */
    public PantsConversionFailedException(String message){
        super(message);
        logger.info("New PantsConversionFailedException with message " + message);
    }

}
