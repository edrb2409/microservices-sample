package io.edrb.app.orderservice.model.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ItemDTO implements Serializable {

    @NotNull(message = "name is required")
    private String name;

    @NotNull
    @DecimalMin(value = "0", message = "price must be greater than zero")
    private BigDecimal price;

    public ItemDTO() {
    }

    public ItemDTO(@NotNull String name, @NotNull @DecimalMin(value = "0") BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return Objects.equals(name, itemDTO.name) &&
                Objects.equals(price, itemDTO.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
