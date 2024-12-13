package com.example.demo.infra.services;

import org.springframework.stereotype.Service;

import com.example.demo.core.interfaces.IFraudService;

@Service
public class FraudService implements IFraudService {

    @Override
    public Boolean isFraud(String cpf) {
        return "12345678902".equals(cpf);
    }

}
