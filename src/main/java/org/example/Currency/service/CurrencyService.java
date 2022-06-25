package org.example.Currency.service;

import org.example.Currency.api.ICurrencyDao;
import org.example.Currency.api.ICurrencyService;
import org.example.Currency.dao.entity.Currency;
import org.example.Currency.dto.CurrencyCreateDto;
import org.example.Currency.dto.CurrencyReadDto;
import org.example.Currency.predicates.IdPredicate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class CurrencyService implements ICurrencyService {
    private final ICurrencyDao dao;
    private final Predicate<Long> idCheck;

    public CurrencyService(ICurrencyDao dao) {
        this.dao = dao;
        this.idCheck = new IdPredicate();
    }


    public Currency get(Long id){
        if(idCheck.test(id)){
            throw new IllegalArgumentException("INVALID ID!");
        }
        return dao.get(id);
    }

    public List<CurrencyReadDto> getAll(){

        List<CurrencyReadDto> readList = new ArrayList<>();
        for (Currency currency : dao.getAll()) {
            readList.add(new CurrencyReadDto(currency));
        }

        return readList;
    }

    public void create(CurrencyCreateDto dto){
        dao.create( new Currency(dto.getName(),
                    dto.getDescription(),
                    dto.getCode(),
                    LocalDateTime.now()) );// <- createDate
    }

    public void update(CurrencyCreateDto currencyCreateDto, Long id, Long updateDateMillis){
        if(idCheck.test(id)){
            throw new IllegalArgumentException("INVALID ID");
        }

        LocalDateTime updateDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(updateDateMillis), ZoneId.systemDefault());//MILLIS --> LOCALDATETIME
        dao.update(currencyCreateDto, id, updateDate);
    }

    public void delete(Long id, Long updateDateMillis){
        if(idCheck.test(id)){
            throw new IllegalArgumentException("INVALID ID");
        }

        LocalDateTime updateDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(updateDateMillis), ZoneId.systemDefault());//MILLIS --> LOCALDATETIME
        dao.delete(id, updateDate);
    }

}
