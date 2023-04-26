package com.example.webbshopbackend1.repositories;

import com.example.webbshopbackend1.models.OrdersProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersProductRepository extends JpaRepository <OrdersProduct, Long> {
}
