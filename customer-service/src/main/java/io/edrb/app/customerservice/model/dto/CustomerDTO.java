package io.edrb.app.customerservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CustomerDTO implements Serializable {

    private String id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "last name is required")
    private String lastName;

    @NotNull(message = "dob is required")
    @Past(message = "dob must not be a future date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;

    @NotEmpty(message = "email address is required")
    @Email(message = "email address must be valid")
    private String email;

    @NotNull
    private Boolean active;

    public CustomerDTO() {}

    protected CustomerDTO(CustomerDTO customerDTO) {
        this.active = customerDTO.active;
        this.dob = customerDTO.dob;
        this.email = customerDTO.email;
        this.id = customerDTO.id;
        this.lastName = customerDTO.lastName;
        this.name = customerDTO.name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(dob, that.dob) &&
                Objects.equals(email, that.email) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, lastName, dob, email, active);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
