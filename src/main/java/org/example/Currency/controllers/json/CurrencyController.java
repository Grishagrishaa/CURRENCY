package org.example.Currency.controllers.json;

import org.example.Currency.api.ICurrencyService;
import org.example.Currency.dao.entity.Currency;
import org.example.Currency.dto.CurrencyCreateDto;
import org.example.Currency.dto.CurrencyReadDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private final ICurrencyService service;

    public CurrencyController(ICurrencyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyReadDto> get(@PathVariable Long id){
        return new ResponseEntity<>(new CurrencyReadDto(service.get(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CurrencyReadDto>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@RequestBody CurrencyCreateDto dto){
        service.create(dto);
    }

    @PutMapping("/{id}/{updateDate}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@RequestBody CurrencyCreateDto dto,
                       @PathVariable Long id,
                       @PathVariable Long updateDate){
        service.update(dto, id, updateDate);
    }


    @DeleteMapping("/{id}/{updateDate}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete( @PathVariable Long id,
                        @PathVariable Long updateDate){
        service.delete(id, updateDate);
    }

}
