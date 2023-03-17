package es.jbp.calculator.core;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import es.jbp.calculator.dto.ErrorResponseDto;
import es.jbp.calculator.exception.CalculatorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@RequiredArgsConstructor
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    private ResponseEntity<ErrorResponseDto> buildResponse(HttpStatus status, Throwable ex) {

        log.error("Error response " + status, ex);

        var errorResponseDto = ErrorResponseDto.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(extractMessageAndCause(ex))
                .build();
        return new ResponseEntity<>(errorResponseDto, status);
    }

    private String extractMessageAndCause(Throwable ex) {
        var message = extractMessage(ex);
        var cause = Optional.ofNullable(ex)
                .map(Throwable::getCause)
                .map(ExceptionAdvice::extractMessage)
                .orElse(null);
        if (cause != null) {
            message += ". Cause: " + cause;
        }
        return message;
    }

    private static String extractMessage(Throwable ex) {
        return  Optional.ofNullable(ex)
                .map(Throwable::getMessage)
                .or(() -> Optional.ofNullable(ex).map(Throwable::toString))
                .orElse(null);
    }

    @ExceptionHandler({
            CalculatorException.class,
            MismatchedInputException.class,
            HttpMessageConversionException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> invalidFormatException(Exception ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ErrorResponseDto> handleOtherException(Exception ex) {
        ex.printStackTrace();
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}
