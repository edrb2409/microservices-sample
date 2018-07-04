package io.edrb.app.orderservice.service.impl;

import io.edrb.app.orderservice.converter.OrderDTOToOrderConverter;
import io.edrb.app.orderservice.converter.OrderToOrderDTOConverter;
import io.edrb.app.orderservice.exception.OrderServiceException;
import io.edrb.app.orderservice.model.Order;
import io.edrb.app.orderservice.model.dto.OrderDTO;
import io.edrb.app.orderservice.repository.OrderRepository;
import io.edrb.app.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final OrderDTOToOrderConverter orderDTOToOrderConverter;

    private final OrderToOrderDTOConverter orderToOrderDTOConverter;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDTOToOrderConverter orderDTOToOrderConverter,
                            OrderToOrderDTOConverter orderToOrderDTOConverter) {
        this.orderRepository = orderRepository;
        this.orderDTOToOrderConverter = orderDTOToOrderConverter;
        this.orderToOrderDTOConverter = orderToOrderDTOConverter;
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        LOGGER.info("Creating an order: {}", orderDTO);

        Order order = orderDTOToOrderConverter.convert(orderDTO);

        return orderToOrderDTOConverter.convert(orderRepository.insert(order));
    }

    @Override
    @CachePut(value = "order-single", key = "#id")
    public OrderDTO update(String id, OrderDTO orderDTO) {
        LOGGER.info("Updating a order: {} - {}", id, orderDTO);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderServiceException("order not found", HttpStatus.NOT_FOUND.value()));

        Order orderForUpdate = orderDTOToOrderConverter.convert(orderDTO);
        orderForUpdate.setId(order.getId());
        orderForUpdate.setCreatedAt(order.getCreatedAt());

        return orderToOrderDTOConverter.convert(orderRepository.save(order));
    }

    @Override
    @CacheEvict(value = "order-single", key = "#id")
    public OrderDTO delete(String id) {
        LOGGER.info("Deleting a order: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderServiceException("order not found", HttpStatus.NOT_FOUND.value()));

        orderRepository.deleteById(order.getId());

        return orderToOrderDTOConverter.convert(order);
    }

    @Override
    @Cacheable(value = "order-single", key = "#id")
    public OrderDTO findBy(String id) {
        LOGGER.info("Search order by: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderServiceException("order not found", HttpStatus.NOT_FOUND.value()));

        return orderToOrderDTOConverter.convert(order);
    }

    @Override
    public List<OrderDTO> findAll(String customerId) {
        LOGGER.info("Retrieving all orders");

        List<Order> orders;

        if(StringUtils.isEmpty(customerId)) {
            orders = orderRepository.findAll();
        } else  {
            orders = orderRepository.findByCustomerId(customerId);
        }

        return orders.stream()
                .map(orderToOrderDTOConverter::convert)
                .collect(Collectors.toList());
    }
}
