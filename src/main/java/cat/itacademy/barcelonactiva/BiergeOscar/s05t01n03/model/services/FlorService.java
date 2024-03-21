package cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.model.services;

import cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.configurations.WebConfig;
import cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.model.dto.FlorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlorService implements IFlorService{

    @Autowired
    private final WebClient webClient;

    //creo constructor para recibir webclient
    public FlorService(WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public Mono<FlorDTO> createFlor(FlorDTO florDTO) {
        Mono<FlorDTO> mono = webClient.post()
                .uri("/add")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //header nomes post and put
                .body(Mono.just(florDTO), FlorDTO.class)
                .retrieve()
                .bodyToMono(FlorDTO.class);
        return mono;
    }

    @Override
    public Mono<FlorDTO> getFlorById(Long id) {
        Mono<FlorDTO> mono = webClient.get()
                .uri("/getOne/{id}", id)
                .retrieve()
                .bodyToMono(FlorDTO.class);
        return mono;
    }

    @Override
    public Mono<FlorDTO> updateFlor(FlorDTO florDTO) {
        Mono<FlorDTO> mono = webClient.put()
                .uri("/update")
                .body(Mono.just(florDTO), FlorDTO.class)
                .retrieve()
                .bodyToMono(FlorDTO.class);
        return mono;
    }

    @Override
    public Mono<Void> deleteFlorById(Long id) {
        Mono<Void> mono = webClient.delete()
                .uri("/delete/{id}", id)
                .retrieve()
                .bodyToMono(void.class);
        return mono;
    }

    @Override
    public Flux<FlorDTO> getAllFlors() {
        Flux<FlorDTO> flux = webClient.get()
                .uri("/getAll")
                .retrieve()
                .bodyToFlux(FlorDTO.class);
        return flux;
    }
}
