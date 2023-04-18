package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "Student Management API", version = "${api.version}",
                contact = @Contact(name = "NienNQ", email = "quangnien24@gmail.com", url = "https://www.facebook.com/quangnien0911"),
                license = @io.swagger.v3.oas.annotations.info.License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"), termsOfService = "${tos.uri}",
                description = "${api.description}"),
        servers = {
                @Server(url = "${niennq.openapi.dev-url}", description = "Development"),
                @Server(url = "${niennq.openapi.prod-url}", description = "Production")})
public class OpenAPI30Configuration {
 
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("My API")
                        .description("Description of my API")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
