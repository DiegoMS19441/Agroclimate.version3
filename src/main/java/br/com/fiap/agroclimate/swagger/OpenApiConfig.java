package br.com.fiap.agroclimate.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "API de gestão agrícola Agroclimate",
                description = "Especificação dos endpoints Agroclimate",
                version = "1.0",
                contact = @Contact(name = "Diego", email = "rm551191@fiap.com.br")
        ),
        security = @SecurityRequirement(name = "bearerJWT")
)
@SecurityScheme(
        name = "bearerJWT",
        description = "Autenticação por Token JWT",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
