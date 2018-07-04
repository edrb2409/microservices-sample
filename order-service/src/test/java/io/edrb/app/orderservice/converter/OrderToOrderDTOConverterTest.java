package io.edrb.app.orderservice.converter;

import io.edrb.app.orderservice.model.Order;
import io.edrb.app.orderservice.model.dto.OrderDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OrderToOrderDTOConverterTest {

    @Test void testThrowAnExceptionWhenOrderIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new OrderToOrderDTOConverter().convert(null));
    }

    @Test void testConvertOrderToOrderDTO() {
        Assertions.assertEquals(expectedDTO(), new OrderToOrderDTOConverter().convert(getOrder()));
    }

    private OrderDTO expectedDTO() {
        OrderDTO dto = new OrderDTO();

        dto.setId("orderId");
        dto.setCompleted(true);
        dto.setCustomerId("customerId");
        dto.setItems(new ArrayList<>());

        return dto;
    }

    private Order getOrder() {
        Order order =  new Order("customerId", new ArrayList<>(), true);
        order.setId("orderId");

        return order;
    }
}
