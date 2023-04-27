package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.models.Orders;
import com.example.webbshopbackend1.models.OrdersProduct;
import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import com.example.webbshopbackend1.repositories.OrderRepository;
import com.example.webbshopbackend1.repositories.ProductRepository;
import com.example.webbshopbackend1.services.ResponseOrderList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;


    @GetMapping("/all")
    public ResponseEntity<ResponseOrderList> all(){
        Iterable<Orders> ordersList = orderRepository.findAll();
        return ResponseEntity.ok(ResponseOrderList.builder()
                .ordersList(ordersList)
                .build());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ResponseOrderList> orderByCustomer(@PathVariable Long id){
        Iterable<Orders> ordersList = orderRepository.findByCustomer(new Customer(id));
        return ResponseEntity.ok(ResponseOrderList.builder()
                .ordersList(ordersList)
                .build());
    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<String> purchase(@PathVariable Long id,
            @RequestBody List<Product> products){
        Orders order = new Orders(customerRepository.findById(id).get());
        order.setOrdersProducts(products.stream().map(product -> new OrdersProduct(order, product)).collect(Collectors.toList()));
        orderRepository.save(order);
        return ResponseEntity.ok("Success!");
    }

}
