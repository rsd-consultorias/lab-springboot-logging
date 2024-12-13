package com.example.demo.infra.services;

import org.springframework.stereotype.Service;

import com.example.demo.core.interfaces.IAmazonService;

@Service
public class AmazonService implements IAmazonService {

    @Override
    public Boolean validateSKU(String sku) {
        return sku.length() >= 10;
    }

}
