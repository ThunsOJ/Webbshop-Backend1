package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/productView")
public class ProductViewController {

    private final ProductRepository repo;

    @RequestMapping("/all")
    public String findAll(Model model){
        List<Product> p = repo.findAll();
        model.addAttribute("allProducts", p);
        model.addAttribute("name", "Name");
        model.addAttribute("price", "Price");
        model.addAttribute("productTitle", "All products");
        return "showAllProducts";
    }

    @RequestMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return repo.findById(id).get();
    }

    @RequestMapping("/add")
    public String addProduct(Model model){
        return "addProduct";
    }

    @PostMapping("/create")
    public String create(@RequestParam String name, double price, Model model) {
        repo.save(new Product(name, price));
        return findAll(model);
    }


}
