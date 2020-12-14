package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WordCounterTest {

    @Test
    public void countTest(){
        Map<String, Integer> count = WordCounter.count("a b c a b c aa bb cc aa bb");
        assertEquals(2,count.get("a"));
        assertEquals(2,count.get("b"));
        assertEquals(2,count.get("c"));
        assertEquals(2,count.get("aa"));
        assertEquals(2,count.get("bb"));
        assertEquals(1,count.get("cc"));
    }
}
