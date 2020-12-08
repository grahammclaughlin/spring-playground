package com.example.demo;

import java.util.Iterator;
import java.util.List;

public class MathService {
    public static int Calculate(String op, int x, int y){
        int result = 0;

        if(op.equals("add")){
            result = x+y;
        }
        else if(op.equals("subtract")){
            result = x-y;
        }
        else if(op.equals("multiply")){
            result = x*y;
        }
        else if(op.equals("divide")){
            result = x/y;
        }
        return result;
    }

    public static String Sum(List<String> nums) {
        Iterator<String> num = nums.listIterator();
        int sum = 0;
        while(num.hasNext()){
            sum = sum + Integer.parseInt(num.next());
        }
        return Integer.toString(sum);
    }
}
