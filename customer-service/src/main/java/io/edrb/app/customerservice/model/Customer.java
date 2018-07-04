package io.edrb.app.customerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    private final String name;

    private final String lastName;

    private final Date dob;

    private final String email;

    private final boolean active;

    public Customer(String name, String lastName, Date dob, String email, boolean active) {
        this.name = name;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.active = active;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return active == customer.active &&
                Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(dob, customer.dob) &&
                Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, lastName, dob, email, active);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
