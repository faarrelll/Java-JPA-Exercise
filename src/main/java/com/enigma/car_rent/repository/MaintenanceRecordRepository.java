package com.enigma.car_rent.repository;

import com.enigma.car_rent.entity.MaintenanceRecord;
import jakarta.persistence.EntityManager;


public class MaintenanceRecordRepository extends GenericRepository<MaintenanceRecord> {

    public MaintenanceRecordRepository(EntityManager entityManager) {
        super(entityManager, MaintenanceRecord.class);
    }
}
