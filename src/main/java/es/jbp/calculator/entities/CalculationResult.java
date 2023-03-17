package es.jbp.calculator.entities;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CalculationResult {

    private BigDecimal resultValue;

    @Override
    public String toString() {
        return "El resultado de la operación es: " + resultValue;
    }
}
