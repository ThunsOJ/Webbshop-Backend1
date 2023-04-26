package com.example.webbshopbackend1.bootstrapping;

import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class CustomerBootstrap {

    private final CustomerRepository customerRepository;

    public List<Customer> addCustomer() {
        Customer customer1 = new Customer(1L, "Ann Al", 860327);
        Customer customer2 = new Customer(2L, "Ludde", 341202);
        Customer customer3 = new Customer(3L, "Hassan", 121005);
        Customer customer4 = new Customer(4L, "Gunilla", 650928);

        List<Customer> cList = Arrays.asList(customer1, customer2, customer3, customer4);
        customerRepository.saveAll(cList);

        return cList;
    }
}