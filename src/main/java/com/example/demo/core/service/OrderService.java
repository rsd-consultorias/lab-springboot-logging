package com.example.demo.core.service;

import java.util.UUID;

import com.example.demo.core.interfaces.IAmazonService;
import com.example.demo.core.interfaces.IBusinessLogService;
import com.example.demo.core.model.dto.CreateOrderDTO;
import com.example.demo.core.model.dto.ServiceResponse;

public class OrderService {
    private final IBusinessLogService businessLogService;
    private final IAmazonService amazonService;

    public OrderService(IAmazonService amazonService, IBusinessLogService businessLogService) {
        this.amazonService = amazonService;
        this.businessLogService = businessLogService;
    }

    public ServiceResponse createOrderV1(CreateOrderDTO createOrderDTO) {
        createOrderDTO.setId(UUID.randomUUID());
        
        businessLogService.info("ORDER", "Creation", "%s".formatted(createOrderDTO.getId()), "Initializing order for CPF %s".formatted(createOrderDTO.getCpf()));

        businessLogService.info("ORDER", "Creation", "%s".formatted(createOrderDTO.getId()),
        "Verifying CPF %s is valid".formatted(createOrderDTO.getCpf()));

        if(createOrderDTO.getCpf().length() != 11) {
            businessLogService.error("ORDER", "Creation", "%s".formatted(createOrderDTO.getId()),
                    "CPF %s is invalid".formatted(createOrderDTO.getCpf()));
            return new ServiceResponse("INVALID_CPF", "Invalid CPF: %s".formatted(createOrderDTO.getCpf()));
        }

        businessLogService.info("ORDER", "Creation", "%s".formatted(createOrderDTO.getId()),
                "Verifying SKU %s is valid".formatted(createOrderDTO.getSku()));

        if (!amazonService.validateSKU(createOrderDTO.getSku())) {
            businessLogService.error("ORDER", "Creation", "%s".formatted(createOrderDTO.getId()),
                    "SKU %s is invalid".formatted(createOrderDTO.getSku()));
            return new ServiceResponse("INVALID_SKU", "Invalid SKU: %s".formatted(createOrderDTO.getSku()));
        }

        businessLogService.info("ORDER", "Creation", "%s".formatted(createOrderDTO.getId()),
                "Verfying CPF can create order for this SKU");

        businessLogService.info("ORDER", "Creation", "%s".formatted(createOrderDTO.getId()), "Order created");

        return new ServiceResponse();
    }

    public Object createOrderV2() {
        return null;
    }
}
