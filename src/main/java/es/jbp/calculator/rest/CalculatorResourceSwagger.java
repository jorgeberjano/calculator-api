package es.jbp.calculator.rest;


import es.jbp.calculator.dto.CalculationRequestDto;
import es.jbp.calculator.dto.CalculationResponseDto;
import es.jbp.calculator.dto.ErrorResponseDto;
import es.jbp.calculator.entities.CalculationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Contrato del recurso calculador que define la documentación swagger open-api
 */
public interface CalculatorResourceSwagger {

    @Operation(summary = "Calculate an arithmetic operation",
            description = "Calculate an arithmetic operation",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CalculationResponseDto.class))
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    ),
                    @ApiResponse(description = "Internal error",
                            responseCode = "500",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    )
            }
    )
    Mono<CalculationResponseDto> calculate(@Valid @RequestBody CalculationRequestDto requestDto);

}
