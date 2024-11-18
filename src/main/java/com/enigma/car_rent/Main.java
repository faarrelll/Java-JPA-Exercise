package com.enigma.car_rent;

import com.enigma.car_rent.constant.car_status;
import com.enigma.car_rent.constant.rental_status;
import com.enigma.car_rent.entity.*;
import com.enigma.car_rent.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.UUID;

public class Main {
    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;

    public static void main(String[] args) {
        init();
        CarsRepository carsRepository = new CarsRepository(entityManager);
        MaintenanceRecordRepository mrr = new MaintenanceRecordRepository(entityManager);
        UsersRepository usersRepository = new UsersRepository(entityManager);
        RentalsRepository rentalsRepository = new RentalsRepository(entityManager);
        PaymentsRepository paymentsRepository = new PaymentsRepository(entityManager);
        Users user = Users.builder()
                .fullName("Farrel Akbar")
                .email("farrel@gmail.com")
                .phoneNumber("088902944883")
                .address("Enigma Camp")
                .drivingLicenseNum("8129ey10e012")
                .passwordHash("safadskjfb129010")
                .build();
        Cars car = Cars.builder()
                .brand("Porsche GT")
                .model("Sports")
                .year(2018)
                .licensePlate("N 1997 XB")
                .color("White")
                .mileage(56d)
                .dailyRate(999d)
                .status(car_status.AVAILABLE)
                .build();
        MaintenanceRecord record = MaintenanceRecord.builder()
                .car(car)
                .maintenanceDate(LocalDateTime.now())
                .description("ganti oli")
                .cost(299999d)
                .completedAt(LocalDateTime.now())
                .build();
        Rentals rentals = Rentals.builder()
                .user(user)
                .car(car)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .totalAmount(1000d)
                .status(rental_status.PENDING)
                .build();
        Payments payment = Payments.builder()
                .rentals(rentals)
                .amount(1000d)
                .paymentMethod("Cash")
                .transactionId(String.valueOf(UUID.randomUUID()))
                .build();
        carsRepository.save(car);
        mrr.save(record);
        usersRepository.save(user);
        rentalsRepository.save(rentals);
        paymentsRepository.save(payment);
    }

    protected static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("car-rent");
        entityManager = entityManagerFactory.createEntityManager();
    }

    protected static void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
