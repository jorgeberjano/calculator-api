package es.jbp.calculator.service;

import es.jbp.calculator.entities.CalculationOperation;
import es.jbp.calculator.entities.CalculationResult;
import es.jbp.calculator.entities.OperationType;
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

    public Mono<CalculationResult> calculate(CalculationOperation calculationOperation) {
        return Mono.just(calculateResult(calculationOperation));
    }

    private CalculationResult calculateResult(CalculationOperation calculationOperation) {

        tracer.trace(calculationOperation);

        var resultValue = calculateResultValue(
                calculationOperation.getOperationType(),
                calculationOperation.getOperands());

        var result = CalculationResult.builder()
                .resultValue(resultValue)
                .build();

        tracer.trace(result);

        return result;
    }

    private BigDecimal calculateResultValue(OperationType operationType, List<BigDecimal> operands) {
        return operationServices.stream()
                .filter(s -> s.getOperationType() == operationType)
                .findFirst()
                .map(s -> s.calculate(operands))
                .orElse(BigDecimal.ZERO);
    }
}
