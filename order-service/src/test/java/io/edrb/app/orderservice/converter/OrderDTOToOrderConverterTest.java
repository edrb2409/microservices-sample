package io.edrb.app.orderservice.converter;

import io.edrb.app.orderservice.model.Order;
import io.edrb.app.orderservice.model.dto.OrderDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OrderDTOToOrderConverterTest {

    @Test
    void testThrowAnExceptionWhenOrderIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new OrderDTOToOrderConverter().convert(null));
    }

    @Test void testConvertOrderToOrderDTO() {
        Assertions.assertEquals(expectedOrder(), new OrderDTOToOrderConverter().convert(getOrderDTO()));
    }

    private Order expectedOrder() {
        return new Order("customerId", new ArrayList<>(), true);
    }

    private OrderDTO getOrderDTO() {
        OrderDTO dto = new OrderDTO();

        dto.setId("orderId");
        dto.setCompleted(true);
        dto.setCustomerId("customerId");
        dto.setItems(new ArrayList<>());

        return dto;
    }
}
