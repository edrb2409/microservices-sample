package io.edrb.app.customerservice.model.dto;

import io.edrb.app.customerservice.model.Order;

import java.io.Serializable;
import java.util.List;

public class CustomerOrdersDTO extends CustomerDTO implements Serializable {

    private List<Order> orders;

    public CustomerOrdersDTO(CustomerDTO customerDTO, List<Order> orders) {
        super(customerDTO);
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
