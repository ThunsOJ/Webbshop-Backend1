package com.example.webbshopbackend1.repositories;

import com.example.webbshopbackend1.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findDistinctFirstByName(String name);
}
