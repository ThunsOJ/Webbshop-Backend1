package com.example.webbshopbackend1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Orders {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable
    private List<Product> products;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    private Date created;
    private Date updated;



    public Orders(Customer customer) {
        this.customer = customer;
    }

    public Orders(Customer customer, List<Product> products) {
        this.products = products;
        this.customer = customer;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

}
