package com.sschudakov.exceptions;

/**
 * Created by Semen Chudakov on 15.11.2017.
 */
public class IllegalTokenException extends IllegalArgumentException{
    public IllegalTokenException() {
    }

    public IllegalTokenException(String s) {
        super(s);
    }

    public IllegalTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTokenException(Throwable cause) {
        super(cause);
    }
}
