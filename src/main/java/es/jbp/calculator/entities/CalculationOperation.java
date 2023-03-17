package es.jbp.calculator.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CalculationOperation {

    private OperationType operationType;

    private List<BigDecimal> operands;

}
