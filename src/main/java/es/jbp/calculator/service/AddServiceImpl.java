package es.jbp.calculator.service;

import es.jbp.calculator.entities.OperationType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementación del servicio de la operación suma
 */
@Service
public class AddServiceImpl implements OperationService {

    @Override
    public OperationType getOperationType() {
        return OperationType.ADD;
    }

    @Override
    public Mono<BigDecimal> calculate(List<BigDecimal> operands) {
        return Mono.just(operands.stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO));
    }
}
