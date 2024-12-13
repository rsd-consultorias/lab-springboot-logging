package com.example.demo.core.interfaces;

public interface IBusinessLogService {
    void info(String process, String step, String correlationId, String message);
    void warn(String process, String step, String correlationId, String message);
    void error(String process, String step, String correlationId, String message);
}
