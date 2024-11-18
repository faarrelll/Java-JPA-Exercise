package com.enigma.car_rent.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class GenericRepository<T> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    // Constructor menerima EntityManager dan Class dari entitas
    public GenericRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    // Save entity
    public void save(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save entity", e);
        }
    }

    // Find entity by ID
    public T findById(Object id) {
        return entityManager.find(entityClass, id);
    }

    // Find all entities
    public List<T> findAll() {
        return entityManager.createQuery("select e from " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }

    // Delete entity
    public void delete(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete entity", e);
        }
    }

    // Update entity
    public void update(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update entity", e);
        }
    }
}
