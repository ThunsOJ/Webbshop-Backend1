package com.example.webbshopbackend1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersProduct {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Orders order;

    @OneToOne
    @JoinColumn
    private Product product;

    public OrdersProduct(Product product) {
        this.product = product;
    }

    public OrdersProduct(Orders order, Product product) {
        this.order = order;
        this.product = product;
    }
}
