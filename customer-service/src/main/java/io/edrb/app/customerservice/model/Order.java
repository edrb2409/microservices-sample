package io.edrb.app.customerservice.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {

    private String id;

    private boolean completed;

    private List<Item> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return completed == order.completed &&
                Objects.equals(id, order.id) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, completed, items);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", completed=" + completed +
                ", items=" + items +
                '}';
    }
}
