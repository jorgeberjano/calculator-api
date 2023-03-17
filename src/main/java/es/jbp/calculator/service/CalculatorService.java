package es.jbp.calculator.service;

import es.jbp.calculator.entities.CalculationOperation;
import es.jbp.calculator.entities.CalculationResult;
import es.jbp.calculator.entities.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculatorService {

    // All operation services injection
    private final List<OperationService> operationServices;

    public Mono<CalculationResult> calculate(CalculationOperation calculationOperation) {
        return Mono.just(CalculationResult.builder()
                .resultValue(calculateResult(calculationOperation.getOperationType(), calculationOperation.getOperands()))
                .build());
    }

    private BigDecimal calculateResult(OperationType operationType, List<BigDecimal> operands) {

        return operationServices.stream()
                .filter(s -> s.getOperationType() == operationType)
                .findFirst()
                .map(s -> s.calculate(operands))
                .orElse(BigDecimal.ZERO);
    }
}