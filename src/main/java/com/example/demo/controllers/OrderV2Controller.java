package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.model.dto.CreateOrderDTO;

@RestController
@RequestMapping("/rest/order/v2")
public class OrderV2Controller {

    @GetMapping
    public String get() {
        return "v2";
    }

    @PostMapping
    public Object post(@RequestBody CreateOrderDTO createOrderDTO) {
        return "NOT IMPLEMENTED";
    }
}