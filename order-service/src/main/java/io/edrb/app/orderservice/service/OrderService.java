package io.edrb.app.orderservice.service;

import io.edrb.app.orderservice.model.dto.OrderDTO;

import java.util.List;

/**
 * Order Service
 *
 * Manage order actions
 */
public interface OrderService {

    /**
     * Creates a new order
     *
     * @param orderDTO order dto
     *
     * @return order created
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * Updates an order
     *
     * @param orderDTO order dto
     *
     * @return order updated
     */
    OrderDTO update(String id, OrderDTO orderDTO);

    /**
     * Deletes a new order
     *
     * @param id order id
     *
     * @return order deleted
     */
    OrderDTO delete(String id);

    /**
     * Find an order for its id
     *
     * @param id order id
     *
     * @return order found
     */
    OrderDTO findBy(String id);

    /**
     * Find all orders that belongs to a customer
     *  if customerId is null or empty, retrieves all orders
     *
     * @param customerId id of a customer
     *
     * @return orders
     */
    List<OrderDTO> findAll(String customerId);
}
