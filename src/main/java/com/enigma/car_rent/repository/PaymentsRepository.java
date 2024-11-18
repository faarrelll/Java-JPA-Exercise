package com.enigma.car_rent.repository;

import com.enigma.car_rent.entity.Payments;
import jakarta.persistence.EntityManager;

public class PaymentsRepository extends GenericRepository<Payments> {
    public PaymentsRepository(EntityManager entityManager) {
        super(entityManager, Payments.class);
    }
}
