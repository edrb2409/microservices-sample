package io.edrb.app.orderservice.repository;

import io.edrb.app.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository for {@link Order}
 *
 */
public interface OrderRepository extends MongoRepository<Order, String> {

    /**
     * Search for all orders that belongs to a customer Id
     * @param customerId id of the customer
     *
     * @return orders found
     */
    List<Order> findByCustomerId(String customerId);
}
