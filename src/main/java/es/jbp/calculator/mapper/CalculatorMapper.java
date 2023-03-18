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

/**
 * Mapper para conversión de objetos Dto y entidades de dominio.
 */
@Mapper(componentModel = "spring")
public interface CalculatorMapper {

    /**
     * Mapea un Dto de petición de cálculo en una operación de calculo
     */
    @Mapping(ignore = true, target = "operands")
    CalculationOperation mapToCalculationOperation(CalculationRequestDto requestDto);

    @AfterMapping
    default void afterMappingToCalculationOperation(CalculationRequestDto requestDto,
                                                    @MappingTarget CalculationOperation.CalculationOperationBuilder calculationOperationBuilder) {
        calculationOperationBuilder.operands(List.of(
                mapToBigDecimal(requestDto.getOperand1()),
                mapToBigDecimal(requestDto.getOperand2())));
    }

    /**
     * Convierte una cadena en un big decimal si es posible, de lo contrario lanza una excepción de cálculo
     */
    default BigDecimal mapToBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch (Throwable ex) {
            throw new CalculatorException("Bad operand value: " + value);
        }
    }

    /**
     * Mapea un resultado de una operacion de calculo en el DTO de respuesta
     */
    CalculationResponseDto mapToCalculationResponseDto(CalculationResult calculationResult);
}


