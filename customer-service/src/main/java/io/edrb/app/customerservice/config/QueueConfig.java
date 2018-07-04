package io.edrb.app.customerservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ configuration class
 *
 */
@Configuration
public class QueueConfig {

    /**
     * Creates deletions queue
     * @param queueName application parameter
     *
     * @return {@link Queue} deletion queue
     */
    @Bean
    public Queue deletionsQueue(@Value("${services.orders.queueName}") String queueName) {
        return new Queue(queueName);
    }

}
