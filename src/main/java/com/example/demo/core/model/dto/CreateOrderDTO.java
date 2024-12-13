package com.example.demo.core.model.dto;

import java.util.UUID;

public class CreateOrderDTO {
    private UUID id;
    private String cpf;
    private String sku;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
