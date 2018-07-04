package io.edrb.app.customerservice.model.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

    private final String message;

    private final int code;

    @JsonCreator
    public ErrorMessage(@JsonProperty(required = true, value = "code") int code,
                        @JsonProperty(required = true, value = "message") String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
