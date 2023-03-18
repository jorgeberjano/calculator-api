package es.jbp.calculator.service;

import es.jbp.calculator.entities.CalculationOperation;
import es.jbp.calculator.entities.CalculationResult;
import reactor.core.publisher.Mono;

/**
 * Contrato para el servicio de calculadora
 */
public interface CalculatorService {

    /**
     * Calcula una operación aritmética.
     * Se implementa de forma reactiva para permitir cálculos en paralelo cuando
     * las operaciones sean mós complejas. Por ejemplo si es una suma de un numero elevado de operandos se podría
     * dividir en varias partes y sumar de manera concurrente cada una de ellas.
     * @param calculationOperation operación a calcular
     * @return El resultado del cálculo
     */
    Mono<CalculationResult> calculate(CalculationOperation calculationOperation);

}