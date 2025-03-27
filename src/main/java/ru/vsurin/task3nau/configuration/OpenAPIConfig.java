package ru.vsurin.task3nau.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Конфигурация Swagger
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Конфигурирует настройки Swagger
     */
    @Bean
    public OpenAPI configureOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");

        Contact contact = new Contact();
        contact.setName("Totheskysx10");

        Info info = new Info()
                .title("TaskManager")
                .version("1.0.0")
                .contact(contact)
                .description("TaskManager");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
