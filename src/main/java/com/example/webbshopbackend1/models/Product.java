package com.example.webbshopbackend1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;

}
