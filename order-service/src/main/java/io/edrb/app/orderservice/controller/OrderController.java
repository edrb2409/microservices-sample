package io.edrb.app.orderservice.controller;

import io.edrb.app.orderservice.model.dto.OrderDTO;
import io.edrb.app.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST Controller for Order CRUD actions
 *
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        LOGGER.info("Creating a order {}", orderDTO);

        return ResponseEntity.ok(orderService.create(orderDTO));
    }


    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAllOrders(@RequestParam(value = "customerId", required = false) String customerId) {
        LOGGER.info("Retrieving all orders for customerId: {}", customerId);

        return ResponseEntity.ok(orderService.findAll(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") String id) {
        LOGGER.info("Retrieving by id: {}", id);

        return ResponseEntity.ok(orderService.findBy(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable("id") String id,
                                                @RequestBody @Valid OrderDTO orderDTO) {
        LOGGER.info("Updating order: {}", id);

        return ResponseEntity.ok(orderService.update(id, orderDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<OrderDTO> deleteOrder(@PathVariable("id") String id) {
        LOGGER.info("Deleting order: {}", id);

        return ResponseEntity.ok(orderService.delete(id));
    }
}
