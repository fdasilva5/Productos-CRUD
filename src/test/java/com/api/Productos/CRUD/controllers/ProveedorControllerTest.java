package com.api.Productos.CRUD.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.http.MediaType;

@SpringBootTest
@WebAppConfiguration
public class ProveedorControllerTest {


    private final static String URL = "/api/proveedores/        ";

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach //se ejecuta antes de cada test
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); //permite simular peticiones http
    }

    @Test
    void testGetProveedores() throws Exception {

        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL)
            .accept(MediaType.APPLICATION_JSON_VALUE))    
        .andReturn();
        assertEquals(200, mockMvcResult.getResponse().getStatus());

    }

    @Test   
    void testSaveProveedor() {

    }
}
