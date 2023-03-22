package com.example.flyright.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
@SecurityScheme(name = "BearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class OpenAPIConfig {

    Contact bolaji = new Contact()
            .name("Abolaji Disu")
            .email("abolajiidiisu@gmail.com")
            .url("https://github.com/OBUSEHEMMANUEL/FlyRight.git");
    @Bean
    public OpenAPI configAPI(){
        return new OpenAPI().info(new Info()
                .title("Smart Vote API Documentation")
                .version("Version 1.00")
                .description("Documentation for Fly Right Booking System")
                .contact(bolaji)
                .termsOfService("An online Flight booking system software platform " +
                        "that allows groups to securely book flights."));
    }
}