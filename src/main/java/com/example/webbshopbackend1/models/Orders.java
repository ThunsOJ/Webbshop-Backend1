package com.example.webbshopbackend1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orders {

    @Id
    @GeneratedValue
    private Long id;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<OrdersProduct> ordersProducts = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn
//    private Customer customer;

    private Date created;
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

}
