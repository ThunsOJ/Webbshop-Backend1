package com.example.webbshopbackend1.controllers;


import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import com.example.webbshopbackend1.repositories.OrderRepository;
import com.example.webbshopbackend1.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebViewController {

    private final ProductRepository repo;
    private final List<Product> products = new ArrayList<>();
    private final CustomerRepository customerRepository;


    @RequestMapping("/start")
    public String showMyPage() {
        return "start.html"; // the name of your HTML file without the extension
    }
    }



