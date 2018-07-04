package io.edrb.app.customerservice.model;

import io.edrb.app.customerservice.model.dto.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

class CustomerDTOTest {

    private static Validator validator;

    @BeforeAll static void init_(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test void testInvalidEmailAddress() throws ParseException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Daniel");
        customerDTO.setLastName("Ron");
        customerDTO.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1986-09-24"));
        customerDTO.setEmail("wrongEmail");
        customerDTO.setActive(true);

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customerDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("email address must be valid", violations.iterator().next().getMessage())
        );
    }

    @Test void testFutureDoBIsNotValid() throws ParseException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Daniel");
        customerDTO.setLastName("Ron");
        customerDTO.setEmail("daniel@example.com");
        customerDTO.setActive(true);

        customerDTO.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-24"));

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customerDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("dob must not be a future date", violations.iterator().next().getMessage())
        );
    }

    @Test void testRequiredCustomerAttributes() throws ParseException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName("Ron");
        customerDTO.setEmail("daniel@example.com");
        customerDTO.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1986-09-24"));
        customerDTO.setActive(true);

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customerDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("name is required", violations.iterator().next().getMessage())
        );
    }

    @Test void testNotEmptyCustomerAttributes() throws ParseException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("");
        customerDTO.setLastName("Ron");
        customerDTO.setEmail("daniel@example.com");
        customerDTO.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1986-09-24"));
        customerDTO.setActive(true);

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customerDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("name is required", violations.iterator().next().getMessage())
        );
    }

    @Test void testValidCustomer() throws ParseException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Daniel");
        customerDTO.setLastName("Ron");
        customerDTO.setEmail("daniel@example.com");
        customerDTO.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1986-09-24"));
        customerDTO.setActive(true);

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customerDTO);

        Assertions.assertTrue(violations.isEmpty());
    }

}
