package com.example.webbshopbackend1.bootstrapping;

import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.ProductRepository;
import org.springframework.context.annotation.Bean;


public class ProductBootstrap {

    final ProductRepository repo;

    public ProductBootstrap(ProductRepository repo) {
        this.repo = repo;
        createProducts();
    }
    public void createProducts(){
        Product p1 = new Product("Wookpanna",99.99);
        Product p2 = new Product("Kexchoklad",867.99);
        Product p3 = new Product("Kalaspuffar",3.99);
        Product p4 = new Product("Pringles",233.99);

        repo.save(p1);
        repo.save(p2);
        repo.save(p3);
        repo.save(p4);
    }
}
