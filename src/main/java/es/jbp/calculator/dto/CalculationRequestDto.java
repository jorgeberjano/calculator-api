package es.jbp.calculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Schema(description="Request Dto for calculation operations")
public class CalculationRequestDto {

    @Schema(description="Unique request identifier for issue tracking", example = "9dbece61-2c25-4394-80fb-02a67d8c97dd")
    private String requestId;

    @Schema(description="Operation type", example = "ADD or SUBTRACT")
    private String operationType;

    @Schema(description="First operand for calculation")
    private String operand1;

    @Schema(description="Second operand for calculation")
    private String operand2;
}
