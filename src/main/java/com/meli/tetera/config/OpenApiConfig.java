package com.meli.tetera.config;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${meli.openapi.dev-url}")
    private String devUrl;

    @Value("${meli.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development server");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Production server");

        Server testServer = new Server();
        testServer.setUrl("http://localhost:8080");
        testServer.setDescription("Test server");

        Contact contact = new Contact();
        contact.setName("Sebastian Neves");
        contact.setEmail("sebastian.neves@mercadolibre.com");
        contact.setUrl("https://github.com/Sebanev15");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info();
        info.title("Swagger in Spring Boot test");
        info.description("This is a simple example of OpenAPI in Spring Boot where i gonna modify properties to show in the Swagger UI");
        info.version("1.0.0");
        info.contact(contact);
        info.license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer, testServer));
    }
}
