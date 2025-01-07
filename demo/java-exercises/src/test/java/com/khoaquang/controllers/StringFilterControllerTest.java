package com.khoaquang.controllers;

import com.khoaquang.services.StringFilterService;
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

@WebMvcTest(StringFilterController.class)
class StringFilterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StringFilterService stringFilterService;

    @Test
    public void testFilterStringStartWith() throws Exception {
        String jsonData = "{\"stringList\":[\"Nam\", \"Hoa\", \"Hien\", \"Hai\"], \"filterValue\":\"h\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/string-filter/start-with")
                .content(jsonData)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void testFilterStringContain() throws Exception {
        String jsonData = "{\"stringList\":[\"Nam\", \"Tien\", \"Hien\", \"Hiep\"], \"filterValue\":\"ie\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/string-filter/contain")
                .content(jsonData)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void testFilterStringContainWithInvalidRequestPayload() throws Exception {
        String jsonData = "{\"stringList\":[], \"filterValue\":\"ie\"}";

        //without request body
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/string-filter/contain")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
        assertEquals("{\"status\":400,\"message\":\"Filter request payload cannot be null.\"}", result.getResponse().getContentAsString());

        //stringList is null/empty
        requestBuilder = MockMvcRequestBuilders
                .post("/string-filter/contain")
                .content(jsonData)
                .contentType(MediaType.APPLICATION_JSON);

        result = mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
        assertEquals("{\"status\":400,\"message\":\"The string list cannot be null/empty.\"}", result.getResponse().getContentAsString());

        //filter value is null/empty
        jsonData = "{\"stringList\":[\"Nam\", \"Tien\", \"Hien\", \"Hiep\"], \"filterValue\":\" \"}";
        requestBuilder = MockMvcRequestBuilders
                .post("/string-filter/contain")
                .content(jsonData)
                .contentType(MediaType.APPLICATION_JSON);

        result = mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
        assertEquals("{\"status\":400,\"message\":\"The filter value cannot be null/empty.\"}", result.getResponse().getContentAsString());
    }
}