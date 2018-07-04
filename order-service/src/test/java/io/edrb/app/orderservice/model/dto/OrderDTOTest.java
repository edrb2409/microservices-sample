package io.edrb.app.orderservice.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderDTOTest {

    private static Validator validator;

    @BeforeAll
    static void init_(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test void testEmptyListOfItems() {
        OrderDTO dto = new OrderDTO();

        dto.setId("orderId");
        dto.setCompleted(true);
        dto.setCustomerId("customerId");
        dto.setItems(new ArrayList<>());

        Set<ConstraintViolation<OrderDTO>> violations = validator.validate(dto);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("items must have at least one item", violations.iterator().next().getMessage())
        );
    }

    @Test void testRequiredCustomerId() {
        OrderDTO dto = new OrderDTO();

        dto.setId("orderId");
        dto.setCompleted(true);
        dto.setItems(items());

        Set<ConstraintViolation<OrderDTO>> violations = validator.validate(dto);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("customerId is required", violations.iterator().next().getMessage())
        );
    }

    @Test void testValidOrderDTO() {
        OrderDTO dto = new OrderDTO();

        dto.setId("orderId");
        dto.setCompleted(true);
        dto.setCustomerId("customerId");
        dto.setItems(items());

        Set<ConstraintViolation<OrderDTO>> violations = validator.validate(dto);

        Assertions.assertTrue(violations.isEmpty());
    }

    private List<ItemDTO> items() {
        return Stream.of(new ItemDTO("item", new BigDecimal(1))).collect(Collectors.toList());
    }
}
