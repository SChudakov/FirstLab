package com.sschudakov.exceptions;

/**
 * Created by Semen Chudakov on 15.11.2017.
 */
public class IllegalSyntaxException extends IllegalArgumentException{
    public IllegalSyntaxException() {
    }

    public IllegalSyntaxException(String s) {
        super(s);
    }

    public IllegalSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalSyntaxException(Throwable cause) {
        super(cause);
    }
}
