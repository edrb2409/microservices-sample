package io.edrb.app.customerservice.repository;

import io.edrb.app.customerservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for {@link Customer}
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
