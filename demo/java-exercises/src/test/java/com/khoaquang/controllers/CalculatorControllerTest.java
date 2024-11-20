package com.khoaquang.controllers;

import com.khoaquang.services.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Test
    public void testAddOperationWithValidParameters() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculator/addition")
                .accept(MediaType.APPLICATION_JSON)
                .param("firstNumber", "8")
                .param("secondNumber", "2.5")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void testAddOperationWithInvalidParameters() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculator/addition")
                .accept(MediaType.APPLICATION_JSON)
                .param("firstNumber", "string")
                .param("secondNumber", "2.5")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSubOperationWithValidParameters() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculator/subtraction")
                .accept(MediaType.APPLICATION_JSON)
                .param("firstNumber", "8")
                .param("secondNumber", "2.5")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void testMulOperationWithValidParameters() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculator/multiplication")
                .accept(MediaType.APPLICATION_JSON)
                .param("firstNumber", "8")
                .param("secondNumber", "2.5")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void testDivOperationWithValidParameters() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculator/division")
                .accept(MediaType.APPLICATION_JSON)
                .param("firstNumber", "8")
                .param("secondNumber", "2.5")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void testDivOperationWithInvalidParameters() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculator/division")
                .accept(MediaType.APPLICATION_JSON)
                .param("firstNumber", "8")
                .param("secondNumber", "0")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
        assertEquals("{\"status\":400,\"message\":\"The second number cannot be 0.\"}", result.getResponse().getContentAsString());
    }
}