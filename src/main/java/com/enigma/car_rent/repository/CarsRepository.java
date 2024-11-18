package com.enigma.car_rent.repository;

import com.enigma.car_rent.entity.Cars;
import jakarta.persistence.EntityManager;

public class CarsRepository extends GenericRepository<Cars> {

    public CarsRepository(EntityManager entityManager) {
        super(entityManager, Cars.class);
    }
}
