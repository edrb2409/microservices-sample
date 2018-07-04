package io.edrb.app.customerservice.service;

import io.edrb.app.customerservice.model.dto.CustomerDTO;
import io.edrb.app.customerservice.model.dto.CustomerOrdersDTO;
import io.edrb.app.customerservice.exception.CustomerServiceException;

import java.util.List;

/**
 * Customer Service
 *
 * Manage customer actions
 */
public interface CustomerService {

    /**
     * Creates a new customer
     *
     * @param customerDTO customer information
     *
     * @return customer created
     */
    CustomerDTO create(CustomerDTO customerDTO);

    /**
     * Updates a customer
     *
     * @param id id of the customer
     * @param customerDTO customer
     *
     * @return customer updated
     *
     * @throws CustomerServiceException if customer not exists
     */
    CustomerDTO update(String id, CustomerDTO customerDTO);

    /**
     * Deletes a customer
     *
     * @param id id of the customer
     *
     * @return customer deleted
     *
     * @throws CustomerServiceException if customer not exists
     */
    CustomerDTO delete(String id);

    /**
     * Find a customer using its ID
     *
     * @param id id of the customer
     *
     * @return Customer
     *
     * @throws CustomerServiceException if customer not exists
     */
    CustomerOrdersDTO findBy(String id);

    /**
     * Retrieve all customers
     *
     * @return all customers
     */
    List<CustomerOrdersDTO> findAll();
}
