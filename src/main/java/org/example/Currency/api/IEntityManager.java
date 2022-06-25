package org.example.Currency.api;

import jakarta.persistence.EntityManager;

public interface IEntityManager extends AutoCloseable{

  EntityManager getEntityManager();

}
