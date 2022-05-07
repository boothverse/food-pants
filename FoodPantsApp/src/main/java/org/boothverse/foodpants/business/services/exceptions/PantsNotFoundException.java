package org.boothverse.foodpants.business.services.exceptions;

/**
 * An exception generated when a specified object or id fails to be found.
 */
public class PantsNotFoundException extends Exception{

    /**
     * An exception generated when a specified object or id fails to be found.
     */
    public PantsNotFoundException() {
        super();
    }

    /**
     * An exception generated when a specified object or id fails to be found.
     *
     * @param message a message describing the reason for the exception
     */
    public PantsNotFoundException(String message){
        super(message);
    }

}
