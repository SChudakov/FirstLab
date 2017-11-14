package com.sschudakov.exceptions;

/**
 * Created by Semen Chudakov on 14.11.2017.
 */
public class MeshHasNoValueException extends RuntimeException {
    public MeshHasNoValueException() {
    }

    public MeshHasNoValueException(String message) {
        super(message);
    }

    public MeshHasNoValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeshHasNoValueException(Throwable cause) {
        super(cause);
    }

    public MeshHasNoValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
