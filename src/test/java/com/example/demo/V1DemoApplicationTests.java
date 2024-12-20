package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.core.interfaces.IAmazonService;
import com.example.demo.core.interfaces.IBusinessLogService;
import com.example.demo.core.interfaces.IFraudService;
import com.example.demo.core.interfaces.IOrderRepository;
import com.example.demo.core.model.Order;
import com.example.demo.core.model.dto.CreateOrderDTO;
import com.example.demo.core.service.OrderService;

@SpringBootTest
class V1DemoApplicationTests {

	private final String CPF_FRAUD = "12345678902";
	private final String CPF_VALID = "12345678901";
	private final String CPF_INVALID = "123456789";

	@Test
	void contextLoads() {
	}

	@Test
	void createOrder_Success() {
		var orderService = new OrderService(new OrderRepository(), new AmazonService(), new BusinessLogService(),
				new FraudService());
		var order = new CreateOrderDTO();
		order.setCpf(CPF_VALID);
		order.setSku("123-456-789");

		var response = orderService.createOrderV1(order);
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	void createOrder_InvalidCPF() {
		var orderService = new OrderService(new OrderRepository(), new AmazonService(), new BusinessLogService(),
				new FraudService());
		var order = new CreateOrderDTO();
		order.setCpf(CPF_INVALID);
		order.setSku("123-456-789");

		var response = orderService.createOrderV1(order);
		assertEquals("FAILED", response.getStatus());
		assertEquals("INVALID_CPF", response.getErrorCode());
		assertEquals("Invalid CPF: %s".formatted(order.getCpf()), response.getMessage());
	}

	@Test
	void createOrder_InvalidSKU() {
		var orderService = new OrderService(new OrderRepository(), new AmazonService(), new BusinessLogService(),
				new FraudService());
		var order = new CreateOrderDTO();
		order.setCpf(CPF_VALID);
		order.setSku("123-456");

		var response = orderService.createOrderV1(order);
		assertEquals("FAILED", response.getStatus());
		assertEquals("INVALID_SKU", response.getErrorCode());
		assertEquals("Invalid SKU: %s".formatted(order.getSku()), response.getMessage());
	}

	@Test
	void findOpenOrderByCPFAndSKU() {
		var repository = new OrderRepository();
		assertNull(repository.findOpenOrderByCPFAndSKU(CPF_INVALID, "123-456-678"));
		assertNotNull(repository.findOpenOrderByCPFAndSKU(CPF_VALID, "123-456-678"));
	}

	// =====================================================
	class OrderRepository implements IOrderRepository {

		@Override
		public Order findOpenOrderByCPFAndSKU(String cpf, String sku) {
			if (CPF_INVALID.equals(cpf))
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

	class FraudService implements IFraudService {

		@Override
		public Boolean isFraud(String cpf) {
			return CPF_FRAUD.equals(cpf);
		}
	}
}
