package io.edrb.app.customerservice.converter;

import io.edrb.app.customerservice.model.Customer;
import io.edrb.app.customerservice.model.dto.CustomerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDTOConverter implements Converter<Customer, CustomerDTO> {

    @Override
    public CustomerDTO convert(Customer customer) {
        if(customer == null) throw new IllegalArgumentException("customer must not be null");

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setDob(customer.getDob());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setActive(customer.isActive());

        return customerDTO;
    }
}
