package io.edrb.app.customerservice.controller.handler;

import io.edrb.app.customerservice.exception.CustomerServiceException;
import io.edrb.app.customerservice.model.error.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Response Entity Handler for order-service
 *
 */
public class CustomerServiceResponseHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceResponseHandler.class);

    /**
     * Handles all {@link CustomerServiceException} exceptions
     *
     * @param exception thrown
     *
     * @return {@link ResponseEntity} with specific error message
     */
    @ExceptionHandler(CustomerServiceException.class)
    private ResponseEntity<ErrorMessage> handleOrderServiceException(CustomerServiceException exception) {
        LOGGER.info("Handling exception: OrderServiceException, message: {}", exception.getMessage());

        return ResponseEntity
                .status(exception.getCode())
                .body(new ErrorMessage(exception.getCode(), exception.getMessage()));
    }

}
