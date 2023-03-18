package es.jbp.calculator.service;

import es.jbp.calculator.entities.OperationType;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

/**
 * Contrato para los servicios de operaciones
 */
public interface OperationService {

    /**
     * Devuelve el tipo de operación que implementa
     * @return
     */
    OperationType getOperationType();

    /**
     * Realiza el cálculo de la operación
     * @param operands Operandos del cálculo
     * @return Resultado del cálculo
     */
    Mono<BigDecimal> calculate(List<BigDecimal> operands);
}
