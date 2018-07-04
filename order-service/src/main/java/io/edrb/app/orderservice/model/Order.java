package io.edrb.app.orderservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private LocalDateTime createdAt;

    private final String customerId;

    private final List<Item> items;

    private final boolean completed;

    public Order(String customerId, List<Item> items, boolean completed) {
        this.customerId = customerId;
        this.items = items;
        this.createdAt = LocalDateTime.now();
        this.completed = completed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return completed == order.completed &&
                Objects.equals(id, order.id) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, items, completed);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", items=" + items +
                ", createdAt=" + createdAt +
                ", completed=" + completed +
                '}';
    }
}
