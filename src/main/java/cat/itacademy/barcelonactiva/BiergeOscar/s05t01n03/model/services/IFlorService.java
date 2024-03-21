package cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.model.services;

import cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.model.dto.FlorDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFlorService {


        //create
    Mono<FlorDTO> createFlor(FlorDTO florDTO);

    //getById
    Mono<FlorDTO> getFlorById(Long id);

    //updateFlor
    Mono<FlorDTO> updateFlor(FlorDTO florDTO);

    //deleteFlorById
    Mono<Void> deleteFlorById(Long id);

    //getAllFlors
    Flux<FlorDTO> getAllFlors();
}
