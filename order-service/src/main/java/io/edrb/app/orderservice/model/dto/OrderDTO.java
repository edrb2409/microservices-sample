package io.edrb.app.orderservice.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OrderDTO implements Serializable {

    private String id;

    @NotNull(message = "customerId is required")
    private String customerId;

    @NotEmpty(message = "items must have at least one item")
    private List<@Valid ItemDTO> items;

    @NotNull(message = "completed is required")
    private boolean completed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return completed == orderDTO.completed &&
                Objects.equals(id, orderDTO.id) &&
                Objects.equals(customerId, orderDTO.customerId) &&
                Objects.equals(items, orderDTO.items);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, customerId, items, completed);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", items=" + items +
                ", completed=" + completed +
                '}';
    }
}
