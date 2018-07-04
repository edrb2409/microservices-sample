package io.edrb.app.orderservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ configuration class
 *
 */
@Configuration
public class RabbitMQConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConfig.class);

    /**
     * Creates deletions queue
     * @param queueName application parameter
     *
     * @return {@link Queue} deletion queue
     */
    @Bean
    public Queue deletionsQueue(@Value("${queueName}") String queueName) {
        LOGGER.info("Creating deletions queue");

        return new Queue(queueName);
    }

}
