package org.boothverse.foodpants.business.services.exceptions;

public class PantsNotFoundException extends Exception{

    public PantsNotFoundException() {
        super();
    }

    public PantsNotFoundException(String message){
        super(message);
    }

}