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
        return Double.toString(MathService.Pi());
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
        int v = MathService.Volume(length,width,height);
        String vstr = Integer.toString(v);
        return "The volume of a "+
                length+"x"+width+"x"+height+
                " rectangle is "+vstr;
    }

    @PostMapping("math/area")
    public String area(@RequestParam Map<String,String> body ){
        double area = 0;
        if(body.containsValue("circle")){
            if(!body.containsKey("radius")){
                return "Invalid";
            }
            area =  MathService.area(
                            Integer.parseInt(body.get("radius"))
                    );
            return "Area of a circle with a radius of "+
                    body.get("radius")+
                    " is "+area;
        }
        else if(body.containsValue("rectangle")){
            if(!(body.containsKey("width") && body.containsKey("height"))){
                return "Invalid";
            }
            area = MathService.area(
                    Integer.parseInt(body.get("width")),
                    Integer.parseInt(body.get("height")));
            return "Area of a "+
                    body.get("width")+
                    "x"+
                    body.get("height")+
                    " rectangle is "+
                    area;
        }
        return "Invalid";
    }
}
