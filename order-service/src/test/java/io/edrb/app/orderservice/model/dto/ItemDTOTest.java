package io.edrb.app.orderservice.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

public class ItemDTOTest {

    private static Validator validator;

    @BeforeAll
    static void init_(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test void testPriceLowerThanZero() {
        ItemDTO itemDTO = new ItemDTO("item", new BigDecimal(-1));

        Set<ConstraintViolation<ItemDTO>> violations = validator.validate(itemDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("price must be greater than zero", violations.iterator().next().getMessage())
        );
    }

    @Test void testNameRequiredMessage() {
        ItemDTO itemDTO = new ItemDTO(null, new BigDecimal(0.12));

        Set<ConstraintViolation<ItemDTO>> violations = validator.validate(itemDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("name is required", violations.iterator().next().getMessage())
        );
    }

    @Test void testValidItemDTO() {
        ItemDTO itemDTO = new ItemDTO("item", new BigDecimal(0.12));

        Set<ConstraintViolation<ItemDTO>> violations = validator.validate(itemDTO);

        Assertions.assertTrue(violations.isEmpty());
    }
}
