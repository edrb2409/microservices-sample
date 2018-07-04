package io.edrb.app.customerservice.service.impl;

import io.edrb.app.customerservice.converter.CustomerDTOToCustomerConverter;
import io.edrb.app.customerservice.converter.CustomerToCustomerDTOConverter;
import io.edrb.app.customerservice.exception.CustomerServiceException;
import io.edrb.app.customerservice.model.Customer;
import io.edrb.app.customerservice.model.dto.CustomerDTO;
import io.edrb.app.customerservice.model.dto.CustomerOrdersDTO;
import io.edrb.app.customerservice.repository.CustomerRepository;
import io.edrb.app.customerservice.service.CustomerService;
import io.edrb.app.customerservice.service.OrderDeletionService;
import io.edrb.app.customerservice.service.OrderServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    private final CustomerDTOToCustomerConverter customerDTOToCustomerConverter;

    private final CustomerToCustomerDTOConverter customerToCustomerDTOConverter;

    private final OrderServiceClient orderServiceClient;

    private final OrderDeletionService orderDeletionService;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerDTOToCustomerConverter customerDTOToCustomerConverter,
                               CustomerToCustomerDTOConverter customerToCustomerDTOConverter,
                               OrderServiceClient orderServiceClient,
                               OrderDeletionService orderDeletionService) {
        this.customerRepository = customerRepository;
        this.customerDTOToCustomerConverter = customerDTOToCustomerConverter;
        this.customerToCustomerDTOConverter = customerToCustomerDTOConverter;
        this.orderServiceClient = orderServiceClient;
        this.orderDeletionService = orderDeletionService;
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customerCreated = customerRepository.insert(customerDTOToCustomerConverter.convert(customerDTO));

        customerDTO.setId(customerCreated.getId());

        return customerDTO;
    }

    @CachePut(value = "customer-single", key = "#id")
    @Override
    public CustomerDTO update(String id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerServiceException("Customer not found", HttpStatus.NOT_FOUND.value()));

        Customer customerForUpdate = customerDTOToCustomerConverter.convert(customerDTO);
        customerForUpdate.setId(customer.getId());

        return customerToCustomerDTOConverter.convert(customerRepository.save(customerForUpdate));
    }

    @CacheEvict(value = "customer-single", key = "#id")
    @Override
    public CustomerDTO delete(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerServiceException("Customer not found", HttpStatus.NOT_FOUND.value()));

        customerRepository.deleteById(id);

        orderDeletionService.send(customer.getId());

        return customerToCustomerDTOConverter.convert(customer);
    }

    @Cacheable(value = "customer-single", key = "#id")
    @Override
    public CustomerOrdersDTO findBy(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerServiceException("Customer not found", HttpStatus.NOT_FOUND.value()));

        return new CustomerOrdersDTO(customerToCustomerDTOConverter.convert(customer),
                orderServiceClient.getOrdersFor(customer.getId()));
    }

    @Override
    public List<CustomerOrdersDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(it -> new CustomerOrdersDTO(customerToCustomerDTOConverter.convert(it),
                        orderServiceClient.getOrdersFor(it.getId())))
                .collect(Collectors.toList());
    }
}
