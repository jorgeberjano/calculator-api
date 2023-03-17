package es.jbp.calculator.service;

import es.jbp.calculator.entities.OperationType;

import java.math.BigDecimal;
import java.util.List;

public interface OperationService {

    OperationType getOperationType();

    BigDecimal calculate(List<BigDecimal> operands);
}
