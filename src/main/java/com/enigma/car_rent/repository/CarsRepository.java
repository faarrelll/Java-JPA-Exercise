package com.enigma.car_rent.repository;

import com.enigma.car_rent.constant.car_status;
import com.enigma.car_rent.entity.Cars;
import com.enigma.car_rent.entity.MaintenanceRecord;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CarsRepository extends GenericRepository<Cars> {
    EntityManager entityManager;

    public CarsRepository(EntityManager entityManager) {
        super(entityManager, Cars.class);
        this.entityManager = entityManager;
    }

    public List<Cars> findCarsAvailable() {
        // Gunakan parameter yang di-bind untuk menghindari SQL Injection dan kesalahan dalam penulisan query
        return entityManager.createQuery("select e from Cars e where e.status = :status", Cars.class)
                .setParameter("status", car_status.AVAILABLE) // Pastikan car_status.AVAILABLE adalah enum yang valid
                .getResultList();
    }
}
