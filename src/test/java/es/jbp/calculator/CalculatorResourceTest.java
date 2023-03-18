package es.jbp.calculator;

import es.jbp.calculator.dto.CalculationRequestDto;
import es.jbp.calculator.dto.CalculationResponseDto;
import es.jbp.calculator.dto.ErrorResponseDto;
import es.jbp.calculator.rest.CalculatorResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Pruebas unitarias del recurso de calculadora (controlador rest)
 */
@WebFluxTest(controllers = CalculatorResource.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CalculatorResourceTest {

    protected final String CALCULATE_ENDPOINT = "/api/calculate";

    @Autowired
    protected WebTestClient webTestClient;

    @Test
    @DisplayName("Petición con respuesta correcta")
    public void calculateOkTest() {

        var request = CalculationRequestDto.builder()
                .operationType("ADD")
                .operand1("5")
                .operand2("2")
                .build();

        var bodySpec = webTestClient.post()
                .uri(CALCULATE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CalculationResponseDto.class);

        var result = bodySpec.returnResult();

        assertEquals(HttpStatus.OK, result.getStatus());

        var response = result.getResponseBody();

        assertNotNull(response);
        assertEquals("7", response.getResultValue());
    }

    @Test
    @DisplayName("Petición con respuesta inccorrecta")
    public void calculateErrorTest() {

        var request = CalculationRequestDto.builder()
                .operationType("ADD")
                .operand1("A")
                .operand2("2")
                .build();

        var bodySpec = webTestClient.post()
                .uri(CALCULATE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorResponseDto.class);

        var result = bodySpec.returnResult();

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatus());

        var response = result.getResponseBody();

        assertNotNull(response);
        assertEquals("Bad Request", response.getError());

    }


}
