package io.edrb.app.customerservice.exception;

public class CustomerServiceException extends RuntimeException {

    private final int code;

    public CustomerServiceException(String message, int code) {
        super(message);
        this.code = code;
    }

    public CustomerServiceException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
