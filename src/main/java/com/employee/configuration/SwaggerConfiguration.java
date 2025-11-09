package com.employee.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Task Management System")
                        .version("1.0")
                        .description("Develop a RESTful API to manage employees and their tasks using Spring Boot and an H2 in-memory database."));
    }
}
