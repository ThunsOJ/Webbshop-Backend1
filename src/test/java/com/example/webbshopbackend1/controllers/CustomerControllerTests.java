package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerController customermocktest;

    @BeforeEach
    public void init() {
        Customer c1 = new Customer(1L, "Terje", 860327);
        Customer c2 = new Customer(2L, "Lennart", 341202);
        Customer c3 = new Customer(3L, "Märit", 121005);

        when(customermocktest.getCustomerById(1L)).thenReturn(c1);
        when(customermocktest.getAllCustomers()).thenReturn(Arrays.asList(c1, c2, c3));
    }

    @Test
    void getAllCustomers() throws Exception {
        this.mockMvc.perform(get("/customer/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Terje\",\"id\":1,\"ssn\":860327}," +
                        "{\"name\":\"Lennart\",\"id\":2,\"ssn\":341202}," +
                        "{\"name\":\"Märit\",\"id\":3,\"ssn\":121005}]"));

    }

    @Test
    void getCustomerById() throws Exception {
        this.mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Terje\",\"id\":1,\"ssn\":860327}"));
    }
}



