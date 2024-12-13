package com.example.demo.core.interfaces;

import com.example.demo.core.model.Order;

public interface IOrderRepository {
    Order findOpenOrderByCPFAndSKU(String cpf, String sku);
}
