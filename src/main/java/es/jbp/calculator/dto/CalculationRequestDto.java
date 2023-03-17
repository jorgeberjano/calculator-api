package es.jbp.calculator.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CalculationRequestDto {

    private String requestId;

    private String operationType;

    private String operand1;

    private String operand2;
}
