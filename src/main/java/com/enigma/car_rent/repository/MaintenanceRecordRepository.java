package com.enigma.car_rent.repository;

import com.enigma.car_rent.entity.MaintenanceRecord;
import jakarta.persistence.EntityManager;

import java.util.List;


public class MaintenanceRecordRepository extends GenericRepository<MaintenanceRecord> {

    EntityManager entityManager;
    public MaintenanceRecordRepository(EntityManager entityManager) {
        super(entityManager, MaintenanceRecord.class);
        this.entityManager = entityManager;
    }
    public List<MaintenanceRecord> findAll(Integer carId) {
        // Gunakan parameter yang di-bind untuk menghindari SQL Injection dan kesalahan dalam penulisan query
        return entityManager.createQuery("select e from MaintenanceRecord e where e.car.id = :carId", MaintenanceRecord.class)
                .setParameter("carId", carId)
                .getResultList();
    }
}
