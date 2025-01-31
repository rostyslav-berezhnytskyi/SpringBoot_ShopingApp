package com.example.springboot_shopingapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
                        contact = @Contact(
                                name = "mem",
                                email = "mem@gmail.com",
                                url = "https://mem.com"),
                        description = "My OnepApi documentation for Spring Boot",
                        title = "OpenApi scecification - mem",
                        version = "1.0",
                        license = @License(
                                name = "License name mem",
                                url = "https://licensesite.com"
                        ),
                        termsOfService = "Terms of service"
                ),
                servers = {
                        @Server(
                                description = "Local ENV",
                                url = "https://localhost:8080"
                        ),
                        @Server(
                                description = "Prod ENV",
                                url = "https://localhost:8081"
                        )}
)
public class OpenApiConfig {
}
