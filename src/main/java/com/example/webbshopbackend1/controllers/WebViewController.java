package com.example.webbshopbackend1.controllers;


import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import com.example.webbshopbackend1.repositories.OrderRepository;
import com.example.webbshopbackend1.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebViewController {

    private final CustomerRepository customerRepository;


    // start sida med meny och registrering
    @RequestMapping("/start")
    public String showMyPage() {
        return "start.html";
    }

 // startsida 2 med formulär för registrering
    @PostMapping("/startlogin")
    public String showMyLoginPage(@RequestParam String name,
                                  @RequestParam(defaultValue = "0") int ssn, Model model) {
        if(name == null || ssn ==0) {
            model.addAttribute("msg", "All fields are required.");
        }
        else if (customerRepository.existsBySsn(ssn)) {
            model.addAttribute("msg", "Customer already exist.");

        } else {
            customerRepository.save(new Customer(name, ssn));
            model.addAttribute("msg", "Successful registration.");

        }
        return "startlogin.html";
    }
}