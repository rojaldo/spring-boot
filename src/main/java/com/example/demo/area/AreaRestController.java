package com.example.demo.area;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1")
public class AreaRestController {

    @Autowired
    private AreaRestService areaRestService;

    @GetMapping("/area")
    public ResponseEntity<Map> getMethodName( 
        @RequestParam(name = "base", required = false, defaultValue = "0.0") float base, 
        @RequestParam(name = "height", required = false, defaultValue = "0.0") float height,
        @RequestParam(name = "radius", required = false, defaultValue = "0.0") float radius) {
        if (radius == 0.0) {
            if (base < 0.0 || height < 0.0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Base and height must be positive"));
            }
            float result = areaRestService.getRectangleArea(base, height);
            String resultString = String.format("Area of a rectangle with base %.2f and height %.2f is %.2f", base, height, result);
            return ResponseEntity.ok(Map.of("base", base, "height", height, "result", result, "resultString", resultString ));
        } else {
            if (base != 0.0 || height != 0.0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "You can only calculate the area of a rectangle or a circle, not both"));
            }
            if (radius < 0.0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Radius must be positive"));
            }
            float result = areaRestService.getCircleArea(radius);
            String resultString = String.format("Area of a circle with radius %.2f is %.2f", radius, result);
            return ResponseEntity.ok(Map.of("radius", radius, "result", result, "resultString", resultString ));
        }
    }
        
    
    
}
