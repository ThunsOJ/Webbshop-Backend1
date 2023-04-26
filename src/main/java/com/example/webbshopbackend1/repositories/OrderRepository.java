package com.example.webbshopbackend1.repositories;

import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Integer> {

    Iterable<Orders> findByCustomer(Customer customer);

}
