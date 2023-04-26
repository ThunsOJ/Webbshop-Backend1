package com.example.webbshopbackend1;

import com.example.webbshopbackend1.bootstrapping.CustomerBootstrap;
import com.example.webbshopbackend1.bootstrapping.OrderBootstrap;
import com.example.webbshopbackend1.bootstrapping.ProductBootstrap;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import com.example.webbshopbackend1.repositories.OrderRepository;
import com.example.webbshopbackend1.repositories.OrdersProductRepository;
import com.example.webbshopbackend1.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebbshopBackend1Application {

    public static void main(String[] args) {
        SpringApplication.run(WebbshopBackend1Application.class, args);
    }

    @Bean
    public CommandLineRunner init(CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository, OrdersProductRepository ordersProductRepository){
        return (args) -> {
            new OrderBootstrap(orderRepository, ordersProductRepository).generateOrders(
                    new CustomerBootstrap(customerRepository).addCustomer(),
                    new ProductBootstrap(productRepository).createProducts());
        };
    }
}
