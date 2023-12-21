package com.example.jwttoken.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact= @Contact(
                        name="Ali Eray KISABACAK",
                        email="eraykisabacak@hotmail.com",
                        url="https://github.com/eraykisabacak"
                ),
                description = "Spring Boot - Spring Security - JWT Tabanlı Kullanıcı Doğrulama, Yetkilendirme, Register, Login ve RabbitMQ ile Email Gönderme Documentation",
                title = "Spring Boot - Spring Security - JWT Tabanlı Kullanıcı Doğrulama, Yetkilendirme, Register, Login ve RabbitMQ ile Email Gönderme Documentation",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
