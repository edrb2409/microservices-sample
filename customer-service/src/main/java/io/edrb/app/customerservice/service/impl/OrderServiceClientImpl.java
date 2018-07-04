package io.edrb.app.customerservice.service.impl;

import io.edrb.app.customerservice.model.Order;
import io.edrb.app.customerservice.service.OrderServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceClientImpl implements OrderServiceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceClient.class);

    private final String ordersURL;

    private final String customerIdQueryParamName;

    private final RestTemplate restTemplate;

    public OrderServiceClientImpl(@Value("${services.orders.baseURL}") String ordersURL,
                                  @Value("${services.orders.queryParam.customerId}") String customerIdQueryParamName,
                                  RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.ordersURL = ordersURL;
        this.customerIdQueryParamName = customerIdQueryParamName;
    }

    @Override
    public List<Order> getOrdersFor(String customerId) {
        LOGGER.info("Retrieving orders for {}", customerId);

        if(StringUtils.isEmpty(customerId))
            throw new IllegalArgumentException("customer must not be null or empty");

        final String uri = UriComponentsBuilder.fromHttpUrl(ordersURL)
                .queryParam(customerIdQueryParamName, customerId)
                .build(false)
                .toUriString();

        LOGGER.info("uri: {}", uri);

        ResponseEntity<List<Order>> responseEntity;

        try {
            responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Order>>() {});

            if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return responseEntity.getBody();
            }

        } catch (RestClientException exception) {
            LOGGER.error("Exception: {}", exception.getMessage(), exception);
        }

        return new ArrayList<>();
    }
}
