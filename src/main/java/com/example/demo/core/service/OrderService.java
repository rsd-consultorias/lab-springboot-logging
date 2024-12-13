package com.example.demo.core.service;

import java.util.UUID;

import com.example.demo.core.interfaces.IAmazonService;
import com.example.demo.core.interfaces.IBusinessLogService;
import com.example.demo.core.interfaces.IOrderRepository;
import com.example.demo.core.model.dto.CreateOrderDTO;
import com.example.demo.core.model.dto.ServiceResponse;

public class OrderService {
    private final IBusinessLogService businessLogService;
    private final IAmazonService amazonService;
    private final IOrderRepository orderRepository;

    public OrderService(
            IOrderRepository orderRepository,
            IAmazonService amazonService,
            IBusinessLogService businessLogService) {
        this.orderRepository = orderRepository;
        this.amazonService = amazonService;
        this.businessLogService = businessLogService;
    }

    public ServiceResponse createOrderV1(CreateOrderDTO createOrderDTO) {
        createOrderDTO.setId(UUID.randomUUID());

        businessLogService.info("ORDER", "Order Creation", "%s".formatted(createOrderDTO.getId()),
                "Initializing order for CPF %s".formatted(createOrderDTO.getCpf()));

        businessLogService.info("ORDER", "Order Creation", "%s".formatted(createOrderDTO.getId()),
                "Verifying if exists Open Order for CPF %s and SKU %s".formatted(createOrderDTO.getCpf(),
                        createOrderDTO.getSku()));
        var openOrder = orderRepository.findOpenOrderByCPFAndSKU(createOrderDTO.getCpf(), createOrderDTO.getSku());
        
        if (openOrder != null) {
            businessLogService.warn("ORDER", "Order Creation", createOrderDTO.getId().toString(),
                    "Already exists an open order for CPF %s and SKU %s: %s".formatted(createOrderDTO.getCpf(),
                            createOrderDTO.getSku(), openOrder.getId().toString()));
        }

        businessLogService.info("ORDER", "Order Creation", "%s".formatted(createOrderDTO.getId()),
                "Verifying CPF %s is valid".formatted(createOrderDTO.getCpf()));

        if (createOrderDTO.getCpf().length() != 11) {
            businessLogService.error("ORDER", "Order Creation", "%s".formatted(createOrderDTO.getId()),
                    "CPF %s is invalid".formatted(createOrderDTO.getCpf()));
            return new ServiceResponse("INVALID_CPF", "Invalid CPF: %s".formatted(createOrderDTO.getCpf()));
        }

        businessLogService.info("ORDER", "Order Creation", "%s".formatted(createOrderDTO.getId()),
                "Verifying SKU %s is valid".formatted(createOrderDTO.getSku()));

        if (!amazonService.validateSKU(createOrderDTO.getSku())) {
            businessLogService.error("ORDER", "Order Creation", "%s".formatted(createOrderDTO.getId()),
                    "SKU %s is invalid".formatted(createOrderDTO.getSku()));
            return new ServiceResponse("INVALID_SKU", "Invalid SKU: %s".formatted(createOrderDTO.getSku()));
        }

        businessLogService.info("ORDER", "Order Creation", "%s".formatted(createOrderDTO.getId()),
                "Verfying CPF can create order for this SKU");

        businessLogService.info("ORDER", "Order Creation", "%s".formatted(createOrderDTO.getId()), "Order created");

        return new ServiceResponse();
    }

    public Object createOrderV2() {
        return null;
    }
}
