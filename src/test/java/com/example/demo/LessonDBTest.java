package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonDBTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception{
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"JPA\", \"deliveredOn\": \"2012-01-14\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class) ));
    }

    @Test
    @Transactional
    @Rollback
    public void testAllLessons() throws Exception{
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"JPA\", \"deliveredOn\": \"2012-01-14\"}");
        MockHttpServletRequestBuilder request2 = get("/lessons");
        this.mvc.perform(request);
        this.mvc.perform(request);
        this.mvc.perform(request);
        this.mvc.perform(request);
        this.mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(4)));
    }

    @Test
    @Transactional
    @Rollback
    public void testOneLesson() throws Exception{
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"JPA\", \"deliveredOn\": \"2012-01-14\"}");
        MockHttpServletRequestBuilder request2 = get("/lessons/8");
        this.mvc.perform(request);
        this.mvc.perform(request);
        this.mvc.perform(request);
        this.mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("JPA")));
    }
}
