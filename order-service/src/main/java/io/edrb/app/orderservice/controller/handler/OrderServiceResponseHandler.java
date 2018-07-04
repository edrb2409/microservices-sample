package io.edrb.app.orderservice.controller.handler;

import io.edrb.app.orderservice.exception.OrderServiceException;
import io.edrb.app.orderservice.model.error.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Response Entity Handler for order-service
 *
 */
public class OrderServiceResponseHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceResponseHandler.class);

    /**
     * Handles all {@link OrderServiceException} exceptions
     *
     * @param exception thrown
     *
     * @return {@link ResponseEntity} with specific error message
     */
    @ExceptionHandler(OrderServiceException.class)
    private ResponseEntity<ErrorMessage> handleCustomerServiceException(OrderServiceException exception) {
        LOGGER.info("Handling exception: CustomerServiceException, message: {}", exception.getMessage());

        return ResponseEntity
                .status(exception.getCode())
                .body(new ErrorMessage(exception.getCode(), exception.getMessage()));
    }

}
