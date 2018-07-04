package io.edrb.app.customerservice.service;

import io.edrb.app.customerservice.MockitoExtension;
import io.edrb.app.customerservice.model.Item;
import io.edrb.app.customerservice.model.Order;
import io.edrb.app.customerservice.service.impl.OrderServiceClientImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceClientTest {

    private OrderServiceClient orderServiceClient;

    private final String orderURL = "http://localhost:8081/order-service/v1/orders";

    private final String customerIdQueryParamName = "customerId";

    @Mock RestTemplate restTemplate;

    @Mock ResponseEntity<List<Order>> mockedOrders;

    @BeforeEach void init_() {
        orderServiceClient = new OrderServiceClientImpl(orderURL, customerIdQueryParamName, restTemplate);
    }

    @Test void testThrownAnIllegalArgumentExceptionWhenCustomerIdIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderServiceClient.getOrdersFor(""));
    }

    @Test void testReturnOrdersForAGivenCustomer() {
        when(restTemplate.exchange("http://localhost:8081/order-service/v1/orders?customerId=customerId",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {})).thenReturn(responseEntity(HttpStatus.OK));

        Assertions.assertEquals(orders(), orderServiceClient.getOrdersFor("customerId"));
    }

    @Test void testReturnEmptyArrayWhenCustomerDoesNotHaveOrders() {
        when(restTemplate.exchange("http://localhost:8081/order-service/v1/orders?customerId=customerId",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {})).thenReturn(emptyResponseEntity());

        Assertions.assertTrue(orderServiceClient.getOrdersFor("customerId").isEmpty());
    }

    @Test void testReturnEmptyArrayWhenOrderServiceSendsAHTTPResponseOtherThanOK() {
        when(restTemplate.exchange("http://localhost:8081/order-service/v1/orders?customerId=customerId",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {})).thenThrow(new RestClientException("Error!!!!"));

        Assertions.assertTrue(orderServiceClient.getOrdersFor("customerId").isEmpty());
    }

    private ResponseEntity<List<Order>> responseEntity(HttpStatus status) {
        return ResponseEntity.status(status).body(orders());
    }

    private ResponseEntity<List<Order>> emptyResponseEntity() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    private List<Order> orders() {
        List<Order> orders = new ArrayList<>();

        Order order = new Order();

        Item item = new Item();
        item.setName("item");
        item.setPrice(new BigDecimal(5.2));

        order.setId("orderId");
        order.setCompleted(true);
        order.setItems(Stream.of(item).collect(Collectors.toList()));

        orders.add(order);

        return orders;
    }
}
