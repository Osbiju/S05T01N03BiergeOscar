package cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.controllers;

import cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.model.services.IFlorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/flor")
public class FlorController {

    @Autowired
    private IFlorService florService;

    @PostMapping("/add")
    public ResponseEntity<Mono<FlorDTO>> createFlor(@RequestBody FlorDTO florDTO){
        return new ResponseEntity<>(florService.createFlor(florDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getOne/{id}")
    public Mono<ResponseEntity<FlorDTO>> getFlorById(@PathVariable("id") Long id){
        return florService.getFlorById(id)
                .map(florDTO -> new ResponseEntity<>(florDTO, HttpStatus.OK))
                .onErrorReturn(
                        HttpClientErrorException.NotFound.class,
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<FlorDTO>> update (@RequestBody FlorDTO florDTOModificada){
        return 	florService.updateFlor(florDTOModificada)
                .map(updateFlor -> new ResponseEntity<>(updateFlor, HttpStatus.OK))
                .onErrorReturn(NotFound.class, new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Long id){
        return florService.deleteFlorById(id)
                .map(updateFlor -> new ResponseEntity<Void>(HttpStatus.OK))
                .onErrorReturn(NotFound.class, new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAll")
    public Mono<ResponseEntity<List<FlorDTO>>> getAll(){
        return florService.getAllFlors()
                .collectList()
                .map(allFlorDTO -> {
                    if (allFlorDTO.isEmpty()){
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(allFlorDTO, HttpStatus.OK);
                    }
                });
    }
}
