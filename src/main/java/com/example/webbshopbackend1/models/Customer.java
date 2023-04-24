package com.example.webbshopbackend1.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int ssn;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> ordersList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return ssn == customer.ssn && id.equals(customer.id) && name.equals(customer.name) && Objects.equals(ordersList, customer.ordersList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

