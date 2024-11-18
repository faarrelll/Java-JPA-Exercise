package com.enigma.car_rent;

import com.enigma.car_rent.constant.car_status;
import com.enigma.car_rent.entity.Cars;
import com.enigma.car_rent.entity.MaintenanceRecord;
import com.enigma.car_rent.entity.Users;
import com.enigma.car_rent.repository.CarsRepository;
import com.enigma.car_rent.repository.MaintenanceRecordRepository;
import com.enigma.car_rent.repository.UsersRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class Main {
    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;

    public static void main(String[] args) {
        init();
        CarsRepository carsRepository = new CarsRepository(entityManager);
        MaintenanceRecordRepository mrr = new MaintenanceRecordRepository(entityManager);
        UsersRepository usersRepository = new UsersRepository(entityManager);
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
        carsRepository.save(car);
        mrr.save(record);
        usersRepository.save(user);
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
