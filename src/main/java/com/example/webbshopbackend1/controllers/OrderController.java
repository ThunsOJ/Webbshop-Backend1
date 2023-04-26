package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Orders;
import com.example.webbshopbackend1.repositories.OrderRepository;
import com.example.webbshopbackend1.services.ResponseOrderList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ResponseOrderList> orderByCustomer(@PathVariable String id){

        Iterable<Orders> ordersList = orderRepository.findAll();

        return ResponseEntity.ok(ResponseOrderList.builder()
                .ordersList(ordersList)
                .build());
    }

}
