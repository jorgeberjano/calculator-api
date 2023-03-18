package es.jbp.calculator.config;

import io.corp.calculator.TracerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de beans de spring que no están anotados como componentes
 */
@Configuration
public class BeanConfiguration {

    /**
     * Bean que implementa el sistema de traza.
     * Realmente el tipo devuelto debería ser la interfaz TracerAPI, pero la clase TracerImpl no implementa dicha
     * interfaz, se ve que es un error en la implementación de la librería tracer.jar
     * @return El bean
     */
    @Bean
    public TracerImpl tracer() {
        return new TracerImpl();
    }
}
