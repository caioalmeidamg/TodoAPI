package br.com.TodoAPI.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Teste de Todo List") //titulo da documentacao
                        .version("v1") //versao da documentacao
                        .description("Uma Api de teste para medir conhecimentos") //descricao da documentacao
                )
                .servers(Collections.singletonList(
                        new Server().url("http://localhost:8080").description("Servidor local")
                ));
    }
}
