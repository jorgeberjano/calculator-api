package es.jbp.calculator;

import es.jbp.calculator.entities.CalculationOperation;
import es.jbp.calculator.entities.OperationType;
import es.jbp.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Pruebas unitarias del servicio de calculadora
 */
@SpringBootTest
class CalculatorServiceTests {

	@Autowired
	protected CalculatorService service;

	@Test
	void contextLoads() {

		var operation = CalculationOperation.builder()
				.operationType(OperationType.ADD)
				.operands(List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2)))
				.build();
		var result = service.calculate(operation).block();

		assertNotNull(result);
		assertEquals(BigDecimal.valueOf(3), result.getResultValue());
	}

}
