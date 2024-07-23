package com.api.jugador.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Jugador")
                        .version("1.0")
                        .description("Descripción de tu API")
                        .termsOfService("http://example.com/terms")
                        .contact(new Contact().name("yamil escobal").email("yamilescobal@gmail.com").url("http://api/equipo"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}