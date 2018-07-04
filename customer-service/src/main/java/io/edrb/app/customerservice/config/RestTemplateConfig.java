package io.edrb.app.customerservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest template configuration class
 *
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Creates a bean for {@link RestTemplate}
     * @param builder template builder
     *
     * @return RestTemplate bean
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
