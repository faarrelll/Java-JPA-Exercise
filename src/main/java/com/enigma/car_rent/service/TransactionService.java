package com.enigma.car_rent.service;

import com.enigma.car_rent.entity.Cars;
import com.enigma.car_rent.entity.Users;
import com.enigma.car_rent.repository.CarsRepository;
import com.enigma.car_rent.repository.PaymentsRepository;
import com.enigma.car_rent.repository.RentalsRepository;
import com.enigma.car_rent.repository.UsersRepository;
import com.enigma.car_rent.utils.ScannerUtils;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TransactionService {
    EntityManager entityManager;
    RentalsRepository rentalsRepository;
    CarsRepository carsRepository;
    PaymentsRepository paymentsRepository;

    public TransactionService(EntityManager entityManager) {
        this.entityManager = entityManager;
        rentalsRepository = new RentalsRepository(entityManager);
        paymentsRepository = new PaymentsRepository(entityManager);
        carsRepository = new CarsRepository(entityManager);
    }

    public void AddTransaction() {
       List<Cars> carsList =  carsRepository.findCarsAvailable();
       carsList.forEach(System.out::println);


    }
}
