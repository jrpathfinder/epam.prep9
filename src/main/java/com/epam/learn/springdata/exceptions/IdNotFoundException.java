package com.epam.learn.springdata.exceptions;

public class IdNotFoundException extends Exception {

    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = 4657491283614755649L;

    public IdNotFoundException(String msg) {
        super(msg);
    }

    public IdNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

}
