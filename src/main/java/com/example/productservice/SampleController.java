package com.example.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {
    @GetMapping("/{name}/{city}")
    public String Hello(@PathVariable("name") String name, @PathVariable("city") String city) {
        return "Hello!! " +name+" From "+city;
    }
}
