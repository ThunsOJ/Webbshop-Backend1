package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.models.Orders;
import com.example.webbshopbackend1.repositories.OrderRepository;
import com.example.webbshopbackend1.services.ResponseOrderList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;


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

    @PostMapping("/buy")
    public ResponseEntity<String> purchase(@RequestBody Orders order){
        orderRepository.save(order);
        return ResponseEntity.ok("Success!");
    }

}
