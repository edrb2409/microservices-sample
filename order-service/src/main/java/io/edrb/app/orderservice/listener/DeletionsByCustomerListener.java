package io.edrb.app.orderservice.listener;

import io.edrb.app.orderservice.model.dto.OrderDTO;
import io.edrb.app.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * RabbitMQ listener for customer deletions
 *
 */
@Component
@RabbitListener(queues = "${queueName}")
public class DeletionsByCustomerListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeletionsByCustomerListener.class);

    private final OrderService orderService;

    public DeletionsByCustomerListener(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Rabbit handler for incoming customer deletion
     *
     * @param customerId Id of the customer
     */
    @RabbitHandler
    public void receiveDeletionRequest(String customerId) {
        if(StringUtils.isEmpty(customerId)) {
            LOGGER.warn("customerId empty or null");
            return;
        }

        orderService.findAll(customerId).stream()
                .map(OrderDTO::getId)
                .forEach(orderService::delete);
    }
}
