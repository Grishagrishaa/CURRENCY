package org.example.Currency.api;

import org.example.Currency.dao.entity.Currency;
import org.example.Currency.dto.CurrencyCreateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface ICurrencyDao extends JpaRepository<Currency, Long>  {
//    Currency get(Long id);
//    List<Currency> getAll();
//    void create(Currency currency);
//    void update(CurrencyCreateDto currencyCreateDto, Long id, LocalDateTime updateDate);
//    void delete(Long id, LocalDateTime updateDate);
}
