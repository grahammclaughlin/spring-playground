package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WordCounterController {

    @PostMapping("/words/count")
    public Map<String, Integer> countWords(
            @RequestBody String str
    ){
        return WordCounter.count(str);
    }

}
