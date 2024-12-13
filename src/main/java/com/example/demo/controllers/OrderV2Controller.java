package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/order/v2")
public class OrderV2Controller {

    @GetMapping
    public String get() {
        return "v2";
    }
}