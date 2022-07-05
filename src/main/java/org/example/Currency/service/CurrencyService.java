package org.example.Currency.service;

import org.example.Currency.api.ICurrencyDao;
import org.example.Currency.api.ICurrencyService;
import org.example.Currency.dao.entity.Currency;
import org.example.Currency.dto.CurrencyCreateDto;
import org.example.Currency.dto.CurrencyReadDto;
import org.example.Currency.predicates.IdPredicate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        checkId(id);

        Currency currency = null;
        if(dao.findById(id).isPresent()){
            currency = dao.findById(id).get();
        }else{
            throw new EntityNotFoundException("INVALID ID, NOT FOUND");
        }

        return currency;
    }

    public List<CurrencyReadDto> getAll(){

        List<CurrencyReadDto> readList = new ArrayList<>();
        for (Currency currency : dao.findAll()) {
            readList.add(new CurrencyReadDto(currency));
        }

        return readList;
    }

    public void create(CurrencyCreateDto dto){
        dao.save( new Currency(dto.getName(),
                    dto.getDescription(),
                    dto.getCode(),
                    LocalDateTime.now()) );// <- createDate
    }



    public void update(CurrencyCreateDto dto, Long id, Long updateDateMillis){
        checkId(id);

        Currency currency = get(id);
                                                     //MILLIS --> LOCALDATETIME
        if(!Objects.equals(currency.getUpdateDate(), convertDate(updateDateMillis))){
            throw new OptimisticLockException("CURRENCY WAS ALREADY UPDATED");
        }

        currency.setName(dto.getName());
        currency.setDescription(dto.getDescription());
        currency.setCode(dto.getCode());
        dao.save(currency);
    }

    public void delete(Long id, Long updateDateMillis){
        checkId(id);

        Currency currency = get(id);
                                                     //MILLIS --> LOCALDATETIME
        if(!Objects.equals(currency.getUpdateDate(), convertDate(updateDateMillis))){
            throw new OptimisticLockException("CURRENCY WAS ALREADY UPDATED");
        }

        dao.deleteById(id);
    }

    private void checkId(Long id){
        if(idCheck.test(id)){
            throw new IllegalArgumentException("INVALID ID!");
        }
    }

    private LocalDateTime convertDate(Long updateDateMillis){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(updateDateMillis), ZoneId.systemDefault());
    }
}
