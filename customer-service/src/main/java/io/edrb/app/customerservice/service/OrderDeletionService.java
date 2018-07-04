package io.edrb.app.customerservice.service;

/**
 * Service for sending deletion payload to a queue
 */
public interface OrderDeletionService {

    /**
     * Send deletion payload to a queue
     *
     * @param customerId id of the customer
     */
    void send(String customerId);
}
