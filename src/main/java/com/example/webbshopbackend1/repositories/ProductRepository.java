package com.example.webbshopbackend1.repositories;

import com.example.webbshopbackend1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
