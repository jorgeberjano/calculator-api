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
}
