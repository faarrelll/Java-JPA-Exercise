package com.enigma.car_rent.service;

import com.enigma.car_rent.constant.car_status;
import com.enigma.car_rent.constant.rental_status;
import com.enigma.car_rent.entity.Cars;
import com.enigma.car_rent.entity.Payments;
import com.enigma.car_rent.entity.Rentals;
import com.enigma.car_rent.entity.Users;
import com.enigma.car_rent.repository.CarsRepository;
import com.enigma.car_rent.repository.PaymentsRepository;
import com.enigma.car_rent.repository.RentalsRepository;
import com.enigma.car_rent.repository.UsersRepository;
import com.enigma.car_rent.utils.ScannerUtils;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    EntityManager entityManager;
    RentalsRepository rentalsRepository;
    CarsRepository carsRepository;
    PaymentsRepository paymentsRepository;
    UsersRepository usersRepository;

    public TransactionService(EntityManager entityManager) {
        this.entityManager = entityManager;
        rentalsRepository = new RentalsRepository(entityManager);
        paymentsRepository = new PaymentsRepository(entityManager);
        carsRepository = new CarsRepository(entityManager);
        usersRepository = new UsersRepository(entityManager);
    }

    public void addTransaction() {
        Rentals rentals = new Rentals();
        Cars cars = new Cars();
        List<Cars> carsList = carsRepository.findCarsAvailable();
        carsList.forEach(System.out::println);
        Integer useriId = ScannerUtils.inputInterger("User Id");
        rentals.setUser(usersRepository.findById(useriId));
        Integer carId = ScannerUtils.inputInterger("Cars Id");
        rentals.setCar(carsRepository.findById(carId));
        rentals.setStartDate(ScannerUtils.inputDate("Start Date"));
        rentals.setEndDate(ScannerUtils.inputDate("End Date"));
        rentals.setStatus(rental_status.PENDING);
        rentals.setTotalAmount(ScannerUtils.inputDouble("Total Amount"));
        rentalsRepository.save(rentals);
    }

    public List<Rentals> getRentals() {
        return rentalsRepository.findAll();
    }

    public void addPayment() {
        Payments payments = new Payments();
        Integer rentalId = ScannerUtils.inputInterger("Rental Id");
        payments.setRentals(rentalsRepository.findById(rentalId));
        payments.setAmount(ScannerUtils.inputDouble("Total Amount"));
        payments.setPaymentDate(LocalDateTime.now());
        payments.setTransactionId(String.valueOf(UUID.randomUUID()));
        paymentsRepository.save(payments);
        Rentals rentals = rentalsRepository.findById(rentalId);
        rentals.setStatus(rental_status.ACTIVE);
        Cars car = carsRepository.findById(rentals.getCar().getId());
        car.setStatus(car_status.RENTED);
        carsRepository.save(car);
    }

    public void cancelPayment() {
        Integer rentalId = ScannerUtils.inputInterger("Rental Id");
        Rentals rentals = rentalsRepository.findById(rentalId);
        rentals.setStatus(rental_status.CANCELLED);
        rentalsRepository.save(rentals);
    }

    public void returnCar(){
        Integer transId = ScannerUtils.inputInterger("Transaction Id");
        Rentals transaction = rentalsRepository.findById(transId);
        Cars car = carsRepository.findById(transaction.getCar().getId());
        car.setStatus(car_status.AVAILABLE);
        transaction.setStatus(rental_status.COMPLETED);
        carsRepository.save(car);
        rentalsRepository.save(transaction);
    }
}
