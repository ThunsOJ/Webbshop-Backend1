package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/customer")
    public class CustomerController {

    private final CustomerRepository customerRepository;

    //visa alla kunder
        @GetMapping("/all")
        public List<Customer> getAllCustomers(){
            return customerRepository.findAll();
        }

        //hitta kund baserat på id
        @GetMapping("/{id}")
        @ResponseBody
        public String getCustomerById(@PathVariable(required = true)
                                              Long id){
            Customer c = customerRepository.findById(id).get();
            return "Customer with the requested ID: " + c;

        }

        //lägg till ny kund
        @PostMapping("/add")
        public String addCustomer(@RequestParam String name, @RequestParam String ssn){
            customerRepository.save(new Customer(name, ssn));
            return "New customer "+name+" was added";
            }


            //ta bort en kund baserat på id
        @DeleteMapping("/delete/{id}")
        public String deleteCustomer(@PathVariable Long id){
            customerRepository.deleteById(id);
            return "The customer with ID "+id+" was deleted.";
        }
}
