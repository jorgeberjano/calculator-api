package es.jbp.calculator.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CalculationRequestDto {

    private String operationType;

    private String operand1;

    private String operand2;
}
