package io.edrb.app.orderservice.converter;

import io.edrb.app.orderservice.model.Order;
import io.edrb.app.orderservice.model.dto.ItemDTO;
import io.edrb.app.orderservice.model.dto.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Converter for {@link Order} to {@link OrderDTO}
 */
@Component
public class OrderToOrderDTOConverter implements Converter<Order, OrderDTO> {

    @Override
    public OrderDTO convert(Order order) {
        if(order == null) throw new IllegalArgumentException("order must not be null");

        OrderDTO dto = new OrderDTO();

        dto.setId(order.getId());
        dto.setCompleted(order.isCompleted());
        dto.setCustomerId(order.getCustomerId());
        dto.setItems(order.getItems().stream().map(it -> new ItemDTO(it.getName(), it.getPrice())).collect(Collectors.toList()));

        return dto;
    }
}
