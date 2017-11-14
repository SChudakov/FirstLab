package com.sschudakov.exceptions;

/**
 * Created by Semen Chudakov on 14.11.2017.
 */
public class SyntaxException extends RuntimeException {

    public SyntaxException() {
    }

    public SyntaxException(String message) {
        super(message);
    }

    public SyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public SyntaxException(Throwable cause) {
        super(cause);
    }

    public SyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
