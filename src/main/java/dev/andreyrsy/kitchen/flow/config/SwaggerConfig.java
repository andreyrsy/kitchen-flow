package dev.andreyrsy.kitchen.flow.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("Kitchen Flow")
                .description("Kitchen Flow API")
                .contact(new Contact().name("Andrey").email("andreyrsy@gmail.com")));
    }
}
