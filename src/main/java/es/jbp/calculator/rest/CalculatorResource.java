package es.jbp.calculator.rest;

import es.jbp.calculator.dto.CalculationRequestDto;
import es.jbp.calculator.dto.CalculationResponseDto;
import es.jbp.calculator.entities.CalculationResult;
import es.jbp.calculator.mapper.CalculatorMapper;
import es.jbp.calculator.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CalculatorResource implements CalculatorResourceSwagger {

    private final CalculatorService calculatorService;

    private final CalculatorMapper calculatorMapper;

    @PostMapping("/calculate")
    public Mono<CalculationResponseDto> calculate(@Valid @RequestBody CalculationRequestDto requestDto) {

        var calculationOperation = calculatorMapper.mapToCalculationOperation(requestDto);

        return calculatorService.calculate(calculationOperation)
                .map(this::process);
    }

    private CalculationResponseDto process(CalculationResult calculationResult) {
        var r = calculatorMapper.mapToCalculationResponseDto(calculationResult);

        return r;
    }
}
