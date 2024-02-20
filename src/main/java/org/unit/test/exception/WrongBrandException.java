package org.unit.test.exception;

public class WrongBrandException extends Exception{
    public WrongBrandException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public WrongBrandException() {
        new WrongBrandException(null, null);
    }

    public WrongBrandException(String errorMessage) {
        new WrongBrandException(errorMessage, null);
    }

}
