package com.example.webbshopbackend1.bootstrapping;


import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.models.Orders;
import com.example.webbshopbackend1.models.OrdersProduct;
import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OrderBootstrap {

    private final OrderRepository orderRepository;

    public void generateOrders(List<Customer> customers, List<Product> products){

        List<OrdersProduct> ordersProductList = new ArrayList<>();

        products.forEach(e->{
            ordersProductList.add(new OrdersProduct(e));
        });

        Orders order1 = new Orders();
        order1.setId(1L);
        order1.setOrdersProducts(List.of(ordersProductList.get(0), ordersProductList.get(1)));
        order1.setCustomer(customers.get(0));

        Orders order2 = new Orders();
        order2.setId(2L);
        order2.setOrdersProducts(List.of(ordersProductList.get(2), ordersProductList.get(3)));
        order2.setCustomer(customers.get(0));

        Orders order3 = new Orders();
        order3.setId(3L);
        order3.setOrdersProducts(List.of(ordersProductList.get(3), ordersProductList.get(3)));
        order3.setCustomer(customers.get(1));

        Orders order4 = new Orders();
        order4.setId(3L);
        order4.setOrdersProducts(List.of(ordersProductList.get(2), ordersProductList.get(0)));
        order4.setCustomer(customers.get(2));

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
    }
}
