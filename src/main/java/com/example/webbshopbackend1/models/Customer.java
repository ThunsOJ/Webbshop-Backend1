package com.example.webbshopbackend1.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Orders> orders;

    private Date created;
    private Date updated;


    public Customer(Long id) {
        this.id = id;
    }

    public Customer(String name, int ssn) {
        this.name = name;
        this.ssn = ssn;
    }

    public Customer(Long id, String name, int ssn) {
        this.id = id;
        this.name = name;
        this.ssn = ssn;
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

