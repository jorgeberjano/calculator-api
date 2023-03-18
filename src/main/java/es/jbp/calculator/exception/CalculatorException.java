package es.jbp.calculator.exception;

/**
 * Excepción para errores en los datos que se usan en las operaciones de cálculo
 */
public class CalculatorException extends RuntimeException {
    public CalculatorException(String message) {
        super(message);
    }
}
