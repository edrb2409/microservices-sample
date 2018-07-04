package io.edrb.app.customerservice.service.impl;

import io.edrb.app.customerservice.service.OrderDeletionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderDeletionImpl implements OrderDeletionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDeletionService.class);

    private final RabbitTemplate rabbitTemplate;

    private final Queue deletionsQueue;

    public OrderDeletionImpl(RabbitTemplate rabbitTemplate, Queue deletionsQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.deletionsQueue = deletionsQueue;
    }

    @Override
    @Async
    public void send(String customerId) {
        LOGGER.info("Sending deletion request for customer: {}", customerId);

        rabbitTemplate.convertAndSend(deletionsQueue.getName(), customerId);
    }
}
