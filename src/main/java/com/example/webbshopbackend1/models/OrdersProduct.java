package com.example.webbshopbackend1.models;

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
    private Orders order;

    @OneToOne
    @JoinColumn
    private Product product;


//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
