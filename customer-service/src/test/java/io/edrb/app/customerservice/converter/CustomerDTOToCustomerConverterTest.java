package io.edrb.app.customerservice.converter;

import io.edrb.app.customerservice.model.Customer;
import io.edrb.app.customerservice.model.dto.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomerDTOToCustomerConverterTest {

    @Test
    void testThrowAnExceptionWhenCustomerDTOIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CustomerDTOToCustomerConverter().convert(null));
    }

    @Test void testConvertCustomerDTOToCustomer() throws ParseException {
        Assertions.assertEquals(expectedCustomer(), new CustomerDTOToCustomerConverter().convert(getCustomerDTO()));
    }

    private Customer expectedCustomer() throws ParseException {
        return new Customer("Daniel",
                "Ron",
                new SimpleDateFormat("yyyy-MM-dd").parse("1986-09-24"),
                "test@domain.com",
                true);
    }

    private CustomerDTO getCustomerDTO() throws ParseException {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId("customerId");
        customerDTO.setName("Daniel");
        customerDTO.setLastName("Ron");
        customerDTO.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1986-09-24"));
        customerDTO.setEmail("test@domain.com");
        customerDTO.setActive(true);

        return customerDTO;
    }
}
