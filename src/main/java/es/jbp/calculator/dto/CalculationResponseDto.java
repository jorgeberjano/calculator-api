package es.jbp.calculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CalculationResponseDto {

    @Schema(description="Unique request identifier for issue tracking", example = "9dbece61-2c25-4394-80fb-02a67d8c97dd")
    private String requestId;

    @Schema(description="Result value of calculation")
    private String resultValue;
}
