package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.models.Orders;
import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import com.example.webbshopbackend1.repositories.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init() {

        Product p1 = new Product(1L,"Wookpanna",99.99);
        Product p2 = new Product(2L,"Kexchoklad",867.99);
        Product p3 = new Product(3L,"Kalaspuffar",3.99);
        Product p4 = new Product(4L,"Pringles",233.99);

        Customer customer1 = new Customer(1L, "Ann Al", 860327);
        Customer customer2 = new Customer(2L, "Ludde", 341202);
        Customer customer3 = new Customer(3L, "Hassan", 121005);

        Orders order1 = new Orders(customer1, List.of(p1, p2));
        Orders order2 = new Orders(customer1, List.of(p3, p2));
        Orders order3 = new Orders(customer2, List.of(p4, p1));
        Orders order4 = new Orders(customer3, List.of(p4, p3));


        when(orderRepository.findByCustomer(customer1)).thenReturn(List.of(order1, order2));
        when(orderRepository.findByCustomer(customer2)).thenReturn(List.of(order3));
        when(orderRepository.findByCustomer(customer3)).thenReturn(List.of(order4));
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2, order3, order4));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));
        when(customerRepository.findById(2L)).thenReturn(Optional.of(customer2));
        when(customerRepository.findById(3L)).thenReturn(Optional.of(customer3));
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2, customer3));
    }

    @Test
    void getAll() throws Exception {

        Product p1 = new Product(1L,"Wookpanna",99.99);
        Product p2 = new Product(2L,"Kexchoklad",867.99);
        Product p3 = new Product(3L,"Kalaspuffar",3.99);
        Product p4 = new Product(4L,"Pringles",233.99);

        Customer customer1 = new Customer(1L, "Ann Al", 860327);
        Customer customer2 = new Customer(2L, "Ludde", 341202);
        Customer customer3 = new Customer(3L, "Hassan", 121005);

        Orders order1 = new Orders(customer1, List.of(p1, p2));
        Orders order2 = new Orders(customer1, List.of(p3, p2));
        Orders order3 = new Orders(customer2, List.of(p4, p1));
        Orders order4 = new Orders(customer3, List.of(p4, p3));

        this.mockMvc.perform(get("/order/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                List.of(order1, order2, order3, order4)
                        )
                ));
    }

    @Test
    void getOrderByCustomer() throws Exception{

        Product p1 = new Product(1L,"Wookpanna",99.99);
        Product p2 = new Product(2L,"Kexchoklad",867.99);
        Product p3 = new Product(3L,"Kalaspuffar",3.99);

        Customer customer1 = new Customer(1L, "Ann Al", 860327);

        Orders order1 = new Orders(customer1, List.of(p1, p2));
        Orders order2 = new Orders(customer1, List.of(p3, p2));

        this.mockMvc.perform(get("/order/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                customer1.getOrders()
                        )
                ));
    }

    @Test
    void makeOrder() throws Exception{

        Product p1 = new Product(1L,"Wookpanna",99.99);
        Product p2 = new Product(2L,"Kexchoklad",867.99);

        this.mockMvc.perform(post("/order/buy/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(
                        List.of(p1, p2)
                )))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("5")));
    }
}
