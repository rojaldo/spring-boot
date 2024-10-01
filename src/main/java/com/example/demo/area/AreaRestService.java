package com.example.demo.area;

import org.springframework.stereotype.Service;

@Service
public class AreaRestService {

    public float getCircleArea(float radius) {
        return (float) (Math.PI * Math.pow(radius, 2));
    }

    public float getRectangleArea(float base, float height) {
        return base * height;
    }
    
}
