package com.enigma.car_rent.service;

import com.enigma.car_rent.constant.car_status;
import com.enigma.car_rent.entity.Cars;
import com.enigma.car_rent.entity.MaintenanceRecord;
import com.enigma.car_rent.repository.CarsRepository;
import com.enigma.car_rent.repository.MaintenanceRecordRepository;
import com.enigma.car_rent.utils.ScannerUtils;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class CarsService {
    EntityManager entityManager;
    CarsRepository carsRepository;
    MaintenanceRecordRepository maintenanceRecordRepository;

    public CarsService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.carsRepository = new CarsRepository(entityManager);
        this.maintenanceRecordRepository = new MaintenanceRecordRepository(entityManager);
    }

    public void addCars() {
        Cars car = new Cars();
        car.setBrand(ScannerUtils.inputString("Brand"));
        car.setModel(ScannerUtils.inputString("Model"));
        car.setColor(ScannerUtils.inputString("Color"));
        car.setYear(ScannerUtils.inputInterger("Year"));
        car.setDailyRate(ScannerUtils.inputDouble("Daily Rate"));
        car.setLicensePlate(ScannerUtils.inputString("LicensePlate"));
        car.setMileage(ScannerUtils.inputDouble("Mileage"));
        car.setStatus(car_status.AVAILABLE);
        carsRepository.save(car);

    }

    public List<Cars> showCars() {
        return carsRepository.findAll();
    }

    public Cars showCarsById(int id) {
        return carsRepository.findById(id);
    }

    public void updateCars() {
        System.out.println("List Of Car");
        List<Cars> allCars = showCars();
        allCars.forEach(System.out::println);
        System.out.println();
        Integer id = Integer.parseInt(ScannerUtils.inputString("Car ID"));
        Cars car = carsRepository.findById(id);
        car.setBrand(ScannerUtils.inputString("Brand"));
        car.setModel(ScannerUtils.inputString("Model"));
        car.setColor(ScannerUtils.inputString("Color"));
        car.setYear(ScannerUtils.inputInterger("Year"));
        car.setDailyRate(ScannerUtils.inputDouble("Daily Rate"));
        car.setLicensePlate(ScannerUtils.inputString("LicensePlate"));
        car.setMileage(ScannerUtils.inputDouble("Mileage"));
        System.out.println("List of Status");
        car_status[] status = car_status.values();
        int i = 1;
        for (car_status car_status : status) {
            System.out.println(i+". "+car_status.toString());
            i++;
        }
        car.setStatus(car_status.valueOf(ScannerUtils.inputString("Status")));
        carsRepository.save(car);
    }

    public void deleteCarsById(int id) {
        Cars cars = carsRepository.findById(id);
        carsRepository.delete(cars);
    }

    public void addMaintenanceRecord() {
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        Cars car = carsRepository.findById(Integer.parseInt(ScannerUtils.inputString("Car ID")));
        maintenanceRecord.setCar(car);
        maintenanceRecord.setMaintenanceDate(ScannerUtils.inputDate("Maintance Date"));
        maintenanceRecord.setDescription(ScannerUtils.inputString("Description"));
        maintenanceRecord.setCost(ScannerUtils.inputDouble("Cost"));
        maintenanceRecord.setCompletedAt(ScannerUtils.inputDate("Maintance Date"));
        maintenanceRecordRepository.save(maintenanceRecord);
    }

    public List<MaintenanceRecord> showMaintenanceRecords() {
        Integer id = Integer.parseInt(ScannerUtils.inputString("Car ID"));
        List<MaintenanceRecord> maintenanceRecords = maintenanceRecordRepository.findAll(id);
        return maintenanceRecords;
    }

}
