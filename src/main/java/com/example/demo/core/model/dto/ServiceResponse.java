package com.example.demo.core.model.dto;

public class ServiceResponse {
    private String status;
    private String errorCode;
    private String message;

    public ServiceResponse() {
        setStatus("SUCCESS");
    }

    public ServiceResponse(String errorCode, String message) {
        setErrorCode(errorCode);
        setMessage(message);
        setStatus("FAILED");
    }

    public ServiceResponse(String status, String errorCode, String message) {
        setErrorCode(errorCode);
        setMessage(message);
        setStatus(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
