package org.boothverse.foodpants.business.dao.exceptions;

public class PantsNotParsedException extends Exception {

    public PantsNotParsedException() {
        super();
    }

    public PantsNotParsedException(String message) {
        super(message);
    }
}
