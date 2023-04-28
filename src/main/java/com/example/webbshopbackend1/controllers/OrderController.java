package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Orders;

import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import com.example.webbshopbackend1.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;


    @GetMapping("/all")
    public ResponseEntity<Iterable<Orders>> all(){
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Iterable<Orders>> orderByCustomer(@PathVariable Long id){
        Iterable<Orders> ordersList = orderRepository.findByCustomer(
                customerRepository.findById(id).get()
        );
        return ResponseEntity.ok(ordersList);
    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<String> purchase(@PathVariable Long id,
            @RequestBody List<Product> products){
        orderRepository.save(new Orders(
                customerRepository.findById(id).get(),
                products
        ));
        return ResponseEntity.ok("Success!");
    }
}
