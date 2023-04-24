package com.example.webbshopbackend1.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProducts {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Order order;

    @OneToMany
    @JoinColumn
    private List<Item> items = new ArrayList<>();


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
