package com.example.webbshopbackend1.bootstrapping;


import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.models.Orders;
import com.example.webbshopbackend1.models.OrdersProduct;
import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderBootstrap {

    private final OrderRepository orderRepository;

    public void generateOrders(List<Customer> customers, List<Product> products){

        //Orders order1 = new Orders();


//        Orders order = new Orders(customerRepository.findById(id).get());
//        order.setOrdersProducts(products.stream().map(product -> new OrdersProduct(order, product)).collect(Collectors.toList()));
//        orderRepository.save(order);

        OrdersProduct product1 = new OrdersProduct(products.get(0));
        OrdersProduct product2 = new OrdersProduct(products.get(1));
        Orders order1 = new Orders(List.of(product1, product2), customers.get(0));
        product1.setOrder(order1);
        product2.setOrder(order1);
        orderRepository.save(order1);
        //order1.setId(1L);
//        Orders order1 = new Orders(List.of(product1, product2), customers.get(0));
////        order1.setOrdersProducts(List.of(product1, product2));
////        order1.setCustomer(customers.get(0));
//        product1.setOrder(order1);
//        product2.setOrder(order1);
//        ordersProductRepository.save(product1);
//        ordersProductRepository.save(product2);

        Orders order2 = new Orders();
        OrdersProduct product3 = new OrdersProduct(products.get(2));
        OrdersProduct product4 = new OrdersProduct(products.get(3));
        //order2.setId(2L);
        order2.setOrdersProducts(List.of(product3, product4));
        order2.setCustomer(customers.get(0));
        product3.setOrder(order2);
        product4.setOrder(order2);
//        ordersProductRepository.save(product3);
//        ordersProductRepository.save(product4);

        Orders order3 = new Orders();
        OrdersProduct product5 = new OrdersProduct(products.get(1));
        OrdersProduct product6 = new OrdersProduct(products.get(0));
        //order3.setId(3L);
        order3.setOrdersProducts(List.of(product5, product6));
        order3.setCustomer(customers.get(1));
        product5.setOrder(order3);
        product6.setOrder(order3);
//        ordersProductRepository.save(product5);
//        ordersProductRepository.save(product6);

        Orders order4 = new Orders();
        OrdersProduct product7 = new OrdersProduct(products.get(3));
        OrdersProduct product8 = new OrdersProduct(products.get(2));
        //order4.setId(4L);
        order4.setOrdersProducts(List.of(product7, product8));
        order4.setCustomer(customers.get(2));
        product7.setOrder(order4);
        product8.setOrder(order4);
//        ordersProductRepository.save(product7);
//        ordersProductRepository.save(product8);

        //orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);

//        ordersProductRepository.save(product1);
//        ordersProductRepository.save(product2);
//        ordersProductRepository.save(product3);
//        ordersProductRepository.save(product4);
//        ordersProductRepository.save(product5);
//        ordersProductRepository.save(product6);
//        ordersProductRepository.save(product7);
//        ordersProductRepository.save(product8);
    }
}
