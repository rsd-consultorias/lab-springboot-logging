package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.core.interfaces.IAmazonService;
import com.example.demo.core.interfaces.IBusinessLogService;
import com.example.demo.core.interfaces.IOrderRepository;
import com.example.demo.core.model.Order;
import com.example.demo.core.model.dto.CreateOrderDTO;
import com.example.demo.core.service.OrderService;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void createOrder_Success() {
		var orderService = new OrderService(new OrderRepository(), new AmazonService(), new BusinessLogService());
		var order = new CreateOrderDTO();
		order.setCpf("12345678901");
		order.setSku("123-456-789");

		var response = orderService.createOrderV1(order);
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	void createOrder_InvalidCPF() {
		var orderService = new OrderService(new OrderRepository(), new AmazonService(), new BusinessLogService());
		var order = new CreateOrderDTO();
		order.setCpf("1234567890");
		order.setSku("123-456-789");

		var response = orderService.createOrderV1(order);
		assertEquals("FAILED", response.getStatus());
		assertEquals("INVALID_CPF", response.getErrorCode());
		assertEquals("Invalid CPF: %s".formatted(order.getCpf()), response.getMessage());
	}

	@Test
	void createOrder_InvalidSKU() {
		var orderService = new OrderService(new OrderRepository(), new AmazonService(), new BusinessLogService());
		var order = new CreateOrderDTO();
		order.setCpf("12345678901");
		order.setSku("123-456");

		var response = orderService.createOrderV1(order);
		assertEquals("FAILED", response.getStatus());
		assertEquals("INVALID_SKU", response.getErrorCode());
		assertEquals("Invalid SKU: %s".formatted(order.getSku()), response.getMessage());
	}

	// =====================================================
	class OrderRepository implements IOrderRepository {

		@Override
		public Order findOpenOrderByCPFAndSKU(String cpf, String sku) {
			if ("45678912312".equals(cpf))
				return null;

			return new Order();
		}

	}

	class AmazonService implements IAmazonService {

		@Override
		public Boolean validateSKU(String sku) {
			return sku.length() >= 10;
		}
	}

	class BusinessLogService implements IBusinessLogService {

		@Override
		public void info(String process, String step, String correlationId, String message) {
		}

		@Override
		public void warn(String process, String step, String correlationId, String message) {
		}

		@Override
		public void error(String process, String step, String correlationId, String message) {
		}

	}
}
