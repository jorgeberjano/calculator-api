package es.jbp.calculator.service;

import es.jbp.calculator.entities.CalculationOperation;
import es.jbp.calculator.entities.CalculationResult;
import es.jbp.calculator.entities.OperationType;
import es.jbp.calculator.exception.CalculatorException;
import io.corp.calculator.TracerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementación del servicio de calculadora
 */
@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

    /**
     * All operation services injection
     */
    private final List<OperationService> operationServices;

    /**
     * Tracer system
     */
    private final TracerImpl tracer;

    /**
     * Devuelve el objeto Mono que calcula el resultado de una operación.
     */
    public Mono<CalculationResult> calculate(CalculationOperation calculationOperation) {

        tracer.trace(calculationOperation);

        var result = calculateResultValue(
                calculationOperation.getOperationType(),
                calculationOperation.getOperands());

        return result.map(this::buildResult);
    }

    private CalculationResult buildResult(BigDecimal value) {
        var result = CalculationResult.builder()
                .resultValue(value)
                .build();
        tracer.trace(result);
        return result;
    }

    private Mono<BigDecimal> calculateResultValue(OperationType operationType, List<BigDecimal> operands) {
        return operationServices.stream()
                .filter(s -> s.getOperationType() == operationType)
                .findFirst()
                .map(s -> s.calculate(operands))
                .orElse(Mono.error(new CalculatorException("Operación no implementada")));
    }
}
