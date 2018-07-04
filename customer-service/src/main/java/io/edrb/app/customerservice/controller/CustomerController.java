package io.edrb.app.customerservice.controller;

import io.edrb.app.customerservice.model.dto.CustomerDTO;
import io.edrb.app.customerservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST Controller for Customer CRUD actions
 *
 */
@RestController()
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        LOGGER.info("Creating a customer: {}", customerDTO);

        return ResponseEntity.ok().body(customerService.create(customerDTO));
    }

    @GetMapping
    public ResponseEntity findAllCustomers() {
        LOGGER.info("Getting all customers");

        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") String id) {
        LOGGER.info("Get user by id: {}", id);

        return ResponseEntity.ok(customerService.findBy(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") String id,
                                                      @RequestBody @Valid CustomerDTO customerDTO) {
        LOGGER.info("Updating customer by {}: {}", id, customerDTO);

        return ResponseEntity.ok(customerService.update(id, customerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable("id") String id) {
        LOGGER.info("Deleting customer {}", id);

        return ResponseEntity.ok(customerService.delete(id));
    }

}
