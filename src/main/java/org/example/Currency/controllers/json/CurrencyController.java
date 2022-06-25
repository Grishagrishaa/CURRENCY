package org.example.Currency.controllers.json;

import org.example.Currency.api.ICurrencyService;
import org.example.Currency.dao.entity.Currency;
import org.example.Currency.dto.CurrencyCreateDto;
import org.example.Currency.dto.CurrencyReadDto;
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
    public CurrencyReadDto get(@PathVariable Long id){
        Currency currency = service.get(id);
        return new CurrencyReadDto(currency);
    }

    @GetMapping
    public List<CurrencyReadDto> getAll(){
        return service.getAll();
    }

    @PostMapping
    public void create (@RequestBody CurrencyCreateDto dto){
        service.create(dto);
    }

    @PutMapping("/{id}/{updateDate}")
    public void update(@RequestBody CurrencyCreateDto dto,
                       @PathVariable Long id,
                       @PathVariable Long updateDate){
        service.update(dto, id, updateDate);
    }


    @DeleteMapping("/{id}/{updateDate}")
    public void delete( @PathVariable Long id,
                        @PathVariable Long updateDate){
        service.delete(id, updateDate);
    }

}
