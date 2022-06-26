package org.example.Currency.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.Currency.api.ICurrencyDao;
import org.example.Currency.api.IEntityManager;
import org.example.Currency.dao.entity.Currency;
import org.example.Currency.dto.CurrencyCreateDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;


public class CurrencyDao implements ICurrencyDao {
    private final IEntityManager manager;

    public CurrencyDao(IEntityManager manager) {
        this.manager = manager;
    }


    public Currency get(Long id){
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();

        Currency currencyFromBase = entityManager.find(Currency.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();

        if(currencyFromBase == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "ENTITY WASN'T FOUND");
//            throw new EntityNotFoundException("ENTITY WASN'T FOUND");
        }

        return currencyFromBase;
    }

    public List<Currency> getAll(){
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Currency> criteria = builder.createQuery(Currency.class);

        // Specify criteria root
        criteria.from(Currency.class);

        // Execute query
        List<Currency> currencyList = entityManager
                .createQuery("Select a from Currency a", Currency.class)
                .getResultList();
        entityManager.getTransaction().commit();


        entityManager.close();
        return currencyList;
    }

    //CurrencyDto -> Transfer data from fields into at service layer -> Currency with 2 null fields(Dates)
    public void create(Currency currency){
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(currency);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(CurrencyCreateDto currencyCreateDto, Long id, LocalDateTime updateDate){
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();

        Currency currencyFromBase = entityManager.find(Currency.class, id);

        if(!currencyFromBase.getUpdateDate().equals(updateDate)){
            throw new IllegalArgumentException("CURRENCY WAS ALREADY UPDATED BY SOMEBODY");
        }
        currencyFromBase.setName(currencyCreateDto.getName());
        currencyFromBase.setDescription(currencyCreateDto.getDescription());
        currencyFromBase.setCode(currencyCreateDto.getCode());

        entityManager.persist(currencyFromBase);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void delete(Long id, LocalDateTime updateDate){
        EntityManager entityManager = manager.getEntityManager();
        entityManager.getTransaction().begin();

        Currency currencyFromBase = entityManager.find(Currency.class, id);

        if(!currencyFromBase.getUpdateDate().equals(updateDate)){
            throw new IllegalArgumentException("CURRENCY WAS ALREADY UPDATED BY SOMEBODY");
        }

        entityManager.remove(currencyFromBase);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
