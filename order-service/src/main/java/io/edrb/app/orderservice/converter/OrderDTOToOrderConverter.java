package io.edrb.app.orderservice.converter;

import io.edrb.app.orderservice.model.Item;
import io.edrb.app.orderservice.model.Order;
import io.edrb.app.orderservice.model.dto.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter for {@link OrderDTO} to {@link Order}
 */
@Component
public class OrderDTOToOrderConverter implements Converter<OrderDTO, Order> {

    @Override
    public Order convert(OrderDTO orderDTO) {
        if(orderDTO == null) throw new IllegalArgumentException("orderDTO must not be null");

        List<Item> items = orderDTO.getItems().stream()
                .map(it -> new Item(it.getName(), it.getPrice()))
                .collect(Collectors.toList());

        return new Order(orderDTO.getCustomerId(), items, orderDTO.isCompleted());
    }
}
