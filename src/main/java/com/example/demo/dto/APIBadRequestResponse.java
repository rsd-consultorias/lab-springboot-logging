package com.example.demo.dto;

public class APIBadRequestResponse {
    private String reason;

    public String getCode() {
        return "400";
    }

    public String getStatusCode() {
        return "BAD REQUEST";
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
