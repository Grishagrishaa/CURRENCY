package org.example.Currency.dto;

public class CurrencyCreateDto {
    private String name;
    private String description;
    private String code;

    public CurrencyCreateDto(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public CurrencyCreateDto() {
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
}
