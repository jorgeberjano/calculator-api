package es.jbp.calculator.entities;

import lombok.*;

import java.math.BigDecimal;

/**
 * Entidad que define el resultado de un cálculo
 */
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
