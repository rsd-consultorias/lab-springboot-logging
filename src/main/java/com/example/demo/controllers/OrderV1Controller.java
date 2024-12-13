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

@RestController
@RequestMapping("/rest/order/v1")
public class OrderV1Controller {

    private final OrderService orderService;

    public OrderV1Controller(OrderRepository orderRepository, AmazonService amazonService, BusinessLogService businessLogService) {
        this.orderService = new OrderService(orderRepository, amazonService, businessLogService);
    }

    @GetMapping
    public String get() {
        return "v1";
    }

    @PostMapping
    public Object post(@RequestBody CreateOrderDTO createOrderDTO) {
        var orderResponse = orderService.createOrderV1(createOrderDTO);

        if ("SUCCESS".equals(orderResponse.getStatus())) {
            return new APISuccessResponse();
        }

        var badRequest = new APIBadRequestResponse();
        badRequest.setReason(orderResponse.getMessage());
        return badRequest;
    }
}