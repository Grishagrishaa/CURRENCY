package org.example.Currency.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Currency.api.IEntityManager;

import java.util.Properties;

public class Manager implements IEntityManager {

  private final EntityManagerFactory sessionFactory;

  public Manager(Properties properties) {
    sessionFactory = Persistence.createEntityManagerFactory("org.example.Currency.dao.entity", properties);
  }

  @Override
  public EntityManager getEntityManager() {
    return sessionFactory.createEntityManager();
  }

  @Override
  public void close() throws Exception {
    sessionFactory.close();
  }
}
