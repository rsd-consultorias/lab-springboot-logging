package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.infra.repositories.OrderRepository;
import com.example.demo.infra.services.AmazonService;
import com.example.demo.infra.services.BusinessLogService;

@RestController
@RequestMapping("/rest/order/latest")
public class OrderLatestController extends OrderV1Controller {

    public OrderLatestController(OrderRepository orderRepository, AmazonService amazonService, BusinessLogService businessLogService) {
        super(orderRepository, amazonService, businessLogService);
    }
}