package io.edrb.app.orderservice.listener;

import io.edrb.app.orderservice.MockitoExtension;
import io.edrb.app.orderservice.model.dto.OrderDTO;
import io.edrb.app.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletionsByCustomerListenerTest {

    private DeletionsByCustomerListener listener;

    @Mock OrderService orderService;

    @BeforeEach void init_() {
        listener = new DeletionsByCustomerListener(orderService);
    }

    @Test void testNotExecutionOfFindByCustomerIdIfCustomerIdIsEmpty() {
        listener.receiveDeletionRequest("");

        verifyZeroInteractions(orderService);
    }

    @Test void testExecutionOfOrderServiceDeleteWhenCustomerIdExists() {
        when(orderService.findAll("customerId")).thenReturn(orderDTOs());

        listener.receiveDeletionRequest("customerId");

        verify(orderService, times(1)).delete("orderId");
    }

    @Test void testNotExecutionOfOrderServiceDeleteWhenCustomerIdNotExists() {
        when(orderService.findAll("customerId0")).thenReturn(new ArrayList<>());

        listener.receiveDeletionRequest("customerId0");

        verify(orderService, times(0)).delete("");
    }

    private List<OrderDTO> orderDTOs() {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        OrderDTO dto = new OrderDTO();

        dto.setId("orderId");
        dto.setCompleted(true);
        dto.setCustomerId("customerId");
        dto.setItems(new ArrayList<>());

        orderDTOS.add(dto);

        return orderDTOS;
    }
}
