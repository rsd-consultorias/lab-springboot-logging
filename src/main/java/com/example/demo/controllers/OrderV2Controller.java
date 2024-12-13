package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.model.dto.CreateOrderDTO;
import com.example.demo.core.service.OrderService;
import com.example.demo.dto.APIBadRequestResponse;
import com.example.demo.dto.APISuccessResponse;
import com.example.demo.infra.repositories.OrderRepository;
import com.example.demo.infra.services.AmazonService;
import com.example.demo.infra.services.BusinessLogService;
import com.example.demo.infra.services.FraudService;

@RestController
@RequestMapping("/rest/order/v2")
public class OrderV2Controller {
    private final OrderService orderService;

    public OrderV2Controller(
        OrderRepository orderRepository, 
        AmazonService amazonService, 
        BusinessLogService businessLogService,
        FraudService fraudService) {
        this.orderService = new OrderService(orderRepository, amazonService, businessLogService, fraudService);
    }

    @GetMapping
    public String get() {
        return "v2";
    }

    @PostMapping
    public Object post(@RequestBody CreateOrderDTO createOrderDTO) {
        var orderResponse = orderService.createOrderV2(createOrderDTO);

        if ("SUCCESS".equals(orderResponse.getStatus())) {
            return new APISuccessResponse();
        }

        var badRequest = new APIBadRequestResponse();
        badRequest.setReason(orderResponse.getMessage());
        return badRequest;
    }
}