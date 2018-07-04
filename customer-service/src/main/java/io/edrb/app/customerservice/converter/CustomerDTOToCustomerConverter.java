package io.edrb.app.customerservice.converter;

import io.edrb.app.customerservice.model.Customer;
import io.edrb.app.customerservice.model.dto.CustomerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOToCustomerConverter implements Converter<CustomerDTO, Customer> {

    @Override
    public Customer convert(CustomerDTO customerDTO) {
        if(customerDTO == null) throw new IllegalArgumentException("CustomerDTO must not be null");

        return new Customer(customerDTO.getName(),
                customerDTO.getLastName(),
                customerDTO.getDob(),
                customerDTO.getEmail(),
                customerDTO.isActive());
    }
}
