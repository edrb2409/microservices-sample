package io.edrb.app.customerservice.converter;

import io.edrb.app.customerservice.model.Customer;
import io.edrb.app.customerservice.model.dto.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomerToCustomerDTOConverterTest {

    @Test void testThrowAnExceptionWhenCustomerIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CustomerToCustomerDTOConverter().convert(null));
    }

    @Test void testConvertCustomerToCustomerDTO() throws ParseException {
        Assertions.assertEquals(expectedCustomerDTO(), new CustomerToCustomerDTOConverter().convert(getCustomer()));
    }

    private CustomerDTO expectedCustomerDTO() throws ParseException {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId("customerId");
        customerDTO.setName("Daniel");
        customerDTO.setLastName("Ron");
        customerDTO.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1986-09-24"));
        customerDTO.setEmail("test@domain.com");
        customerDTO.setActive(true);

        return customerDTO;
    }

    private Customer getCustomer() throws ParseException {
        Customer customer =  new Customer("Daniel",
                "Ron",
                new SimpleDateFormat("yyyy-MM-dd").parse("1986-09-24"),
                "test@domain.com",
                true);

        customer.setId("customerId");

        return customer;
    }
}
