package com.example.webbshopbackend1.bootstrapping;

import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
public class ProductBootstrap {

    final ProductRepository repo;


    @Bean
    public List<Product> createProducts(){
        Product p1 = new Product("Wookpanna",99.99);
        Product p2 = new Product("Kexchoklad",867.99);
        Product p3 = new Product("Kalaspuffar",3.99);
        Product p4 = new Product("Pringles",233.99);

        List<Product> pList = Arrays.asList(p1, p2, p3, p4);

        repo.saveAll(pList);

        return pList;
    }

}
