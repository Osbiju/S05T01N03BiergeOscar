package cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.*;


@Configuration
public class WebConfig {

    @Bean
    WebClient webClient(){
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9001/flor")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return webClient;
    }
}
