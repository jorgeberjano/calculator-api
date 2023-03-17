package es.jbp.calculator.service;

import es.jbp.calculator.entities.OperationType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Add operation service
 */
@Service
public class AddService implements OperationService {

    @Override
    public OperationType getOperationType() {
        return OperationType.ADD;
    }

    @Override
    public BigDecimal calculate(List<BigDecimal> operands) {
        return operands.stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
