package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Customer;
import com.example.webbshopbackend1.models.Orders;
import com.example.webbshopbackend1.models.OrdersProduct;
import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.CustomerRepository;
import com.example.webbshopbackend1.repositories.OrderRepository;
import com.example.webbshopbackend1.services.ResponseOrderList;
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


@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    //@MockBean
    //private CustomerRepository customerRepository;

    @BeforeEach
    public void init() {

        Product product1 = new Product(1L, "Lingonsylt", 59.00);
        Product product2 = new Product(2L, "Toapapper", 69.00);
        Product product3 = new Product(3L, "Elcykel", 13999.00);
        Product product4 = new Product(4L, "Servis", 999.00);

        Customer customer1 = new Customer(1L, "Kajsa", 111111);
        Customer customer2 = new Customer(2L, "Greta", 222222);
        Customer customer3 = new Customer(3L, "Jennifer", 333333);

        OrdersProduct ordersProduct1 = new OrdersProduct(product1);
        OrdersProduct ordersProduct2 = new OrdersProduct(product2);
        Orders order1 = new Orders(List.of(ordersProduct1, ordersProduct2), customer1);
        ordersProduct1.setOrder(order1);
        ordersProduct2.setOrder(order1);

        OrdersProduct ordersProduct3 = new OrdersProduct(product2);
        OrdersProduct ordersProduct4 = new OrdersProduct(product3);
        Orders order2 = new Orders(List.of(ordersProduct3, ordersProduct4), customer1);
        ordersProduct3.setOrder(order2);
        ordersProduct4.setOrder(order2);

        OrdersProduct ordersProduct5 = new OrdersProduct(product3);
        OrdersProduct ordersProduct6 = new OrdersProduct(product4);
        Orders order3 = new Orders(List.of(ordersProduct5, ordersProduct6), customer2);
        ordersProduct5.setOrder(order3);
        ordersProduct6.setOrder(order3);

        OrdersProduct ordersProduct7 = new OrdersProduct(product3);
        OrdersProduct ordersProduct8 = new OrdersProduct(product1);
        Orders order4 = new Orders(List.of(ordersProduct7, ordersProduct8), customer3);
        ordersProduct7.setOrder(order4);
        ordersProduct8.setOrder(order4);


        when(orderRepository.findByCustomer(customer1)).thenReturn(List.of(order1, order2));
        when(orderRepository.findByCustomer(customer2)).thenReturn(List.of(order3));
        when(orderRepository.findByCustomer(customer3)).thenReturn(List.of(order4));
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2, order3, order4));
    }

    @Test
    void getAll() throws Exception {

        Product product1 = new Product(1L, "Lingonsylt", 59.00);
        Product product2 = new Product(2L, "Toapapper", 69.00);
        Product product3 = new Product(3L, "Elcykel", 13999.00);
        Product product4 = new Product(4L, "Servis", 999.00);

        Customer customer1 = new Customer(1L, "Kajsa", 111111);
        Customer customer2 = new Customer(2L, "Greta", 222222);
        Customer customer3 = new Customer(3L, "Jennifer", 333333);

        OrdersProduct ordersProduct1 = new OrdersProduct(product1);
        OrdersProduct ordersProduct2 = new OrdersProduct(product2);
        Orders order1 = new Orders(List.of(ordersProduct1, ordersProduct2), customer1);
        ordersProduct1.setOrder(order1);
        ordersProduct2.setOrder(order1);

        OrdersProduct ordersProduct3 = new OrdersProduct(product2);
        OrdersProduct ordersProduct4 = new OrdersProduct(product3);
        Orders order2 = new Orders(List.of(ordersProduct3, ordersProduct4), customer1);
        ordersProduct3.setOrder(order2);
        ordersProduct4.setOrder(order2);

        OrdersProduct ordersProduct5 = new OrdersProduct(product3);
        OrdersProduct ordersProduct6 = new OrdersProduct(product4);
        Orders order3 = new Orders(List.of(ordersProduct5, ordersProduct6), customer2);
        ordersProduct5.setOrder(order3);
        ordersProduct6.setOrder(order3);

        OrdersProduct ordersProduct7 = new OrdersProduct(product3);
        OrdersProduct ordersProduct8 = new OrdersProduct(product1);
        Orders order4 = new Orders(List.of(ordersProduct7, ordersProduct8), customer3);
        ordersProduct7.setOrder(order4);
        ordersProduct8.setOrder(order4);

        this.mockMvc.perform(get("/order/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                ResponseOrderList.builder()
                                        .ordersList(List.of(order1, order2, order3, order4))
                                        .build()
                        )
                ));
    }

    @Test
    void getOrderByCustomer() throws Exception{

        Product product1 = new Product(1L, "Lingonsylt", 59.00);
        Product product2 = new Product(2L, "Toapapper", 69.00);
        Product product3 = new Product(3L, "Elcykel", 13999.00);

        Customer customer1 = new Customer(1L, "Kajsa", 111111);

        OrdersProduct ordersProduct1 = new OrdersProduct(product1);
        OrdersProduct ordersProduct2 = new OrdersProduct(product2);
        Orders order1 = new Orders(List.of(ordersProduct1, ordersProduct2), customer1);
        ordersProduct1.setOrder(order1);
        ordersProduct2.setOrder(order1);

        OrdersProduct ordersProduct3 = new OrdersProduct(product2);
        OrdersProduct ordersProduct4 = new OrdersProduct(product3);
        Orders order2 = new Orders(List.of(ordersProduct3, ordersProduct4), customer1);
        ordersProduct3.setOrder(order2);
        ordersProduct4.setOrder(order2);

        this.mockMvc.perform(get("/order/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                ResponseOrderList.builder()
                                        .ordersList(List.of(order1, order2))
                                        .build()
                        )
                ));
    }

    @Test
    void makeOrder() throws Exception{

        Product product3 = new Product(3L, "Elcykel", 13999.00);
        Product product4 = new Product(4L, "Servis", 999.00);

        this.mockMvc.perform(post("/order/buy/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(
                        List.of(product4, product3)
                )))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Success!")));
    }
}
