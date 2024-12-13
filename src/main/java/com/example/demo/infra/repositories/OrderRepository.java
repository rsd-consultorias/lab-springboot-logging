package com.example.demo.infra.repositories;

import org.springframework.stereotype.Service;

import com.example.demo.core.interfaces.IOrderRepository;
import com.example.demo.core.model.Order;

@Service
public class OrderRepository implements IOrderRepository {

    @Override
    public Order findOpenOrderByCPFAndSKU(String cpf, String sku) {
        return new Order();
    }

}
