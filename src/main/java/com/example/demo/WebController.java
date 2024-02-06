package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @PostMapping("/hello")
    public String hello(@RequestBody InputParamDto dto) {
        return "id: " + dto.getId();
    }
}