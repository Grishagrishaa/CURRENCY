package org.example.Currency.api;

import org.example.Currency.dao.entity.Currency;
import org.example.Currency.dto.CurrencyCreateDto;
import org.example.Currency.dto.CurrencyReadDto;

import java.util.List;

public interface ICurrencyService {
    Currency get(Long id);
    List<CurrencyReadDto> getAll();
    void create(CurrencyCreateDto dto);
    void update(CurrencyCreateDto currencyCreateDto, Long id, Long updateDateMillis);
    void delete(Long id, Long updateDateMillis);
}
