package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/math/pi")
    public String pi(){
        return Double.toString(Math.PI);
    }

    @GetMapping("/math/calculate")
    public String calculate(@RequestParam Map<String,String> requestParams){
        String op;
        if(requestParams.containsKey("operation")){
            op = requestParams.get("operation");
        }
        else {
            op = "add";
        }

        int x = Integer.parseInt(requestParams.get("x"));
        int y = Integer.parseInt(requestParams.get("y"));

        return Integer.toString(MathService.Calculate(op,x,y));
    }

    @PostMapping("/math/sum")
    public String sum(@RequestParam MultiValueMap<String, String> requestParams){
        if(!requestParams.containsKey("n")){
            return "";
        }
        List<String> nums = requestParams.get("n");
        return MathService.Sum(nums);
    }

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String volume(
            @PathVariable int length,
            @PathVariable int width,
            @PathVariable int height){
        return Integer.toString(length*width*height);
    }
}
