package io.edrb.app.orderservice.exception;

public class OrderServiceException extends RuntimeException {

    private final int code;

    public OrderServiceException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

