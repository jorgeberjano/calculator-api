package es.jbp.calculator.service;

import es.jbp.calculator.entities.OperationType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Subtract operation service
 */
@Service
public class SubtractService implements OperationService {

    @Override
    public OperationType getOperationType() {
        return OperationType.SUBTRACT;
    }

    @Override
    public BigDecimal calculate(List<BigDecimal> operands) {
        return operands.stream()
                .reduce(BigDecimal::subtract)
                .orElse(BigDecimal.ZERO);
    }
}
