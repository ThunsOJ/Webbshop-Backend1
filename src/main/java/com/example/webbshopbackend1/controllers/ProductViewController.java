package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Orders;
import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import com.example.webbshopbackend1.repositories.OrderRepository;
import com.example.webbshopbackend1.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/productView")
public class ProductViewController {

    private final ProductRepository repo;
    private final List<Product> products = new ArrayList<>();

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private boolean isOrdered;

    @RequestMapping("/all")
    public String findAll(Model model){

        if (isOrdered){
            products.clear();
        }

        List<Product> p = repo.findAll();
        model.addAttribute("allProducts", p);
        model.addAttribute("name", "Name");
        model.addAttribute("price", "Price");
        model.addAttribute("buy", "Buy");
        model.addAttribute("productTitle", "All products");
        model.addAttribute("cartTitle", "Items in cart");
        model.addAttribute("allCLicked",products);
        return "showAllProducts";
    }

    @RequestMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        products.add(repo.findById(id).get());
        return findAll(model);
    }

    @RequestMapping("/add")
    public String addProduct(Model model){
        return "addProduct";
    }
    @RequestMapping("/order")
    public String addOrder(Model model){
        return "order";
    }

    @RequestMapping("/clear")
    public String empty(Model model){
        products.clear();
        return findAll(model);
    }


    @PostMapping("/create")
    public String create(@RequestParam String name, double price, Model model) {
        repo.save(new Product(name, price));
        return findAll(model);
    }


    @PostMapping("/buy")
    public String purchase(@RequestParam String fname, Model model){
        orderRepository.save(new Orders(
                customerRepository.findDistinctFirstByName(fname),
                products
        ));
        model.addAttribute("name", "Name");
        model.addAttribute("price", "Price");
        model.addAttribute("buy", "Buy");
        model.addAttribute("productTitle", "All products");
        model.addAttribute("cartTitle", "Items in cart");
        model.addAttribute("allCLicked",products);
        model.addAttribute("fname", fname);

        isOrdered = true;
        return "orderCompleted";
    }




}
