package org.example.Currency.dto;


import org.example.Currency.dao.entity.Currency;

import java.time.ZoneId;

public class CurrencyReadDto {

    private Long id;
    private String name;
    private String description;
    private String code;

    private Long createDateEpoch;
    private Long updateDateEpoch;

    public CurrencyReadDto(Long id, String name, String description, String code, Long createDateEpoch, Long updateDateEpoch) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;

        this.createDateEpoch = createDateEpoch;
        this.updateDateEpoch = updateDateEpoch;
    }

    public CurrencyReadDto(Currency currencyEntity) {
        this.id = currencyEntity.getId();
        this.name = currencyEntity.getName();
        this.description = currencyEntity.getDescription();
        this.code = currencyEntity.getCode();

        //LOCALDATETIME --> MILLIS
        this.createDateEpoch = currencyEntity.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.updateDateEpoch = currencyEntity.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public CurrencyReadDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCreateDateEpoch() {
        return createDateEpoch;
    }

    public void setCreateDateEpoch(Long createDateEpoch) {
        this.createDateEpoch = createDateEpoch;
    }

    public Long getUpdateDateEpoch() {
        return updateDateEpoch;
    }

    public void setUpdateDateEpoch(Long updateDateEpoch) {
        this.updateDateEpoch = updateDateEpoch;
    }
}
