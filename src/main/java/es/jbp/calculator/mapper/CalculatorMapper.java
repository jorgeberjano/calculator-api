package es.jbp.calculator.mapper;

import es.jbp.calculator.exception.CalculatorException;
import es.jbp.calculator.dto.CalculationRequestDto;
import es.jbp.calculator.dto.CalculationResponseDto;
import es.jbp.calculator.entities.CalculationOperation;
import es.jbp.calculator.entities.CalculationResult;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CalculatorMapper {

    @Mapping(ignore = true, target = "operands")
    CalculationOperation mapToCalculationOperation(CalculationRequestDto requestDto);

    @AfterMapping
    default void afterMappingToCalculationOperation(CalculationRequestDto requestDto,
                                                    @MappingTarget CalculationOperation.CalculationOperationBuilder calculationOperationBuilder) {
        calculationOperationBuilder.operands(List.of(
                mapToBigDecimal(requestDto.getOperand1()),
                mapToBigDecimal(requestDto.getOperand2())));
    }

    default BigDecimal mapToBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch (Throwable ex) {
            throw new CalculatorException("Bad operand value: " + value);
        }
    }

    CalculationResponseDto mapToCalculationResponseDto(CalculationResult calculationResult);
}


