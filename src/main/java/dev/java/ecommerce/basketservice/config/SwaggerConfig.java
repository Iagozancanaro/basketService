package dev.java.ecommerce.basketservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {

        Contact contact = new Contact();
        contact.name("Iago");
        contact.email("iagozancanaro@gmail.com");

        Info info = new Info();
        info.title("BasketService");
        info.version("v1");
        info.description("Aplicação para gerenciamento de um carrinho de compras");
        info.contact(contact);

        return new OpenAPI().info(info);
    }
}
