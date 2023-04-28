package com.example.webbshopbackend1.bootstrapping;


import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.models.Orders;
import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderBootstrap {

    private final OrderRepository orderRepository;

    public void generateOrders(List<Customer> customers, List<Product> products){

        Orders order1 = new Orders(customers.get(0), List.of(products.get(0), products.get(2)));
        Orders order2 = new Orders(customers.get(0), List.of(products.get(1), products.get(3)));
        Orders order3 = new Orders(customers.get(1), List.of(products.get(3), products.get(0)));
        Orders order4 = new Orders(customers.get(2), List.of(products.get(2), products.get(1)));

        orderRepository.saveAll(List.of(order1,order2,order3,order4));
    }
}
