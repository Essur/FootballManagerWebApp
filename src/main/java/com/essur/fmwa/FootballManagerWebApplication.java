package com.essur.fmwa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Endpoints library",
        version = "1.0",
        description = "Endpoints library of Football manager web app API"))
public class FootballManagerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballManagerWebApplication.class, args);
    }
}
