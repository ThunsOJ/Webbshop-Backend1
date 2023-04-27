package com.example.webbshopbackend1.controllers;

import com.example.webbshopbackend1.models.Product;
import com.example.webbshopbackend1.repositories.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository mockrepo;

    @BeforeEach
    public void init(){
        Product p1 = new Product(1L,"Wookpanna",99.99);
        Product p2 = new Product(2L,"Kexchoklad",867.99);

        when(mockrepo.findById(1L)).thenReturn(Optional.of(p1));
        when(mockrepo.findById(2L)).thenReturn(Optional.of(p2));

        when(mockrepo.findAll()).thenReturn(Arrays.asList(p1,p2));
    }


    @Test
    void findAll() throws Exception {
        this.mockMvc.perform(get("/product/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Wookpanna\",\"id\":1,\"price\":99.99}," +
                        "{\"name\":\"Kexchoklad\",\"id\":2,\"price\":867.99}]"));
    }

    @Test
    void findById() throws Exception {
        this.mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Wookpanna\",\"id\":1,\"price\":99.99}"));
    }

    @Test
    void addProduct() throws Exception {
        this.mockMvc.perform(post("/product/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Groda\",\"id\":3,\"price\":999.99}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"name\":\"Groda\",\"id\":3,\"price\":999.99}"));
    }
}