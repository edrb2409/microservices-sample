package io.edrb.app.customerservice.service;

import io.edrb.app.customerservice.model.Order;

import java.util.List;

/**
 * Client for connecting to order service
 */
public interface OrderServiceClient {

    /**
     * Gets all orders that belongs to a customer
     *
     * @param customerId id of the customer
     *
     * @return order
     */
    List<Order> getOrdersFor(String customerId);

}
