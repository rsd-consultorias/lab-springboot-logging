package com.example.demo.core.model;

import java.util.UUID;

public class Order {
    private UUID id;

    public Order() {
        setId(UUID.randomUUID());
    }

    public Order(UUID id) {
        setId(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
