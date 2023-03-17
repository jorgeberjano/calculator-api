package es.jbp.calculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Schema(description="Error response information")
public class ErrorResponseDto {

    @Schema(description="Response status code", example = "400, 500, ...")
    private int status;

    @Schema(description="Error type", example = "Bad Request, Internal Error, ...")
    private String error = "";

    @Schema(description="Descriptive error message", example = "Missing attribute ...")
    private String message;

    @Schema(description="Unique request identifier for issue tracking", example = "9dbece61-2c25-4394-80fb-02a67d8c97dd")
    private String requestId;
}
