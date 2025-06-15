package com.andrevsc.keybook.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                // ✅ ADICIONADO: Define que o esquema de segurança deve ser aplicado globalmente
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // ✅ ADICIONADO: Define o esquema de segurança (JWT Bearer Token)
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                // Seção de informações que já tínhamos
                .info(new Info()
                        .title("Keybook API")
                        .version("v1.0")
                        .description("API para a aplicação Keybook, que gerencia usuários, tabelas, itens, propriedades e comentários.")
                        .license(new License().name("Apache 2.0").url("http://spring.io"))
                );
    }
}