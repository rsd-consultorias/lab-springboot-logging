package com.example.demo.infra.services;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.example.demo.core.interfaces.IBusinessLogService;

@Service
public class BusinessLogService implements IBusinessLogService {

    @Override
    public void info(String process, String step, String correlationId, String message) {
        System.out.println("%s  INFO  [%s] [%s] [%s]: %s".formatted(Instant.now(), correlationId, process,
                step, message));
    }

    @Override
    public void warn(String process, String step, String correlationId, String message) {
        System.out.println("%s  WARN  [%s] [%s] [%s]: %s".formatted(Instant.now(), correlationId, process,
                step, message));
    }

    @Override
    public void error(String process, String step, String correlationId, String message) {
        System.out.println("%s  ERROR [%s] [%s] [%s]: %s".formatted(Instant.now(), correlationId, process,
                step, message));
    }

}
