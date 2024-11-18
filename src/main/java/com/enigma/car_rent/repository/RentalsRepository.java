package com.enigma.car_rent.repository;

import com.enigma.car_rent.entity.Rentals;
import jakarta.persistence.EntityManager;

public class RentalsRepository extends GenericRepository<Rentals>{
    public RentalsRepository(EntityManager entityManager) {
        super(entityManager, Rentals.class);
    }
}
