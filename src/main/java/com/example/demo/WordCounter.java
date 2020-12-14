package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.*;


public class WordCounter {


    public static Map<String, Integer> count(String s){
        s = s.toLowerCase();
        Map<String,Integer> map = new HashMap<>();
        String[] words = s.split("[^A-z]+");
        for(String word: words){
            if(map.containsKey(word)){
                int i = map.get(word);
                map.replace(word,++i);
;            }
            else{
                map.put(word,1);
            }
        }
        return map;
    }
}
