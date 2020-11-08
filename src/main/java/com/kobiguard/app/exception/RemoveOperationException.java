package com.kobiguard.app.exception;

public class RemoveOperationException extends RuntimeException {

    public RemoveOperationException(String message) {
        super(message);
    }

    public RemoveOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoveOperationException(Throwable cause) {
        super(cause);
    }
}
