package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JSONController.class)
public class JSONControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void testFlight() throws Exception{
        MockHttpServletRequestBuilder request = get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs",is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].passenger.firstName",is("Some name")))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName",is("Some other name")))
                .andExpect(jsonPath("$.tickets[0].price",is(200)));
    }

    @Test
    public void testFlights() throws Exception{
        MockHttpServletRequestBuilder request = get("/flights")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                //first flight
                .andExpect(jsonPath("$[0].departs",is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName",is("Some name")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName",is("Some other name")))
                .andExpect(jsonPath("$[0].tickets[0].price",is(200)))
                //second flight
                .andExpect(jsonPath("$[1].departs",is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.firstName",is("Some other name")))
                //.andExpect(jsonPath("$[1].tickets[0].passenger.lastName",is(null)))
                .andExpect(jsonPath("$[1].tickets[0].price",is(400)));
    }
}


