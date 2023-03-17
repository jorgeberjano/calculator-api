package es.jbp.calculator.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CalculationOperation {

    private OperationType operationType;

    private List<BigDecimal> operands;

    @Override
    public String toString() {
        return "Operación " + operationType + " con operandos: "
                + operands.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
    }

}
