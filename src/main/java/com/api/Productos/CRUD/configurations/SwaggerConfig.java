package com.api.Productos.CRUD.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    @Bean
    public OpenAPI productoOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Productos API")
                        .description("API para la gestión de productos")
                        .version("1.0"));
    
    }
}
