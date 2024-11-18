package com.enigma.car_rent.view;

import com.enigma.car_rent.entity.Cars;
import com.enigma.car_rent.entity.MaintenanceRecord;
import com.enigma.car_rent.service.CarsService;
import com.enigma.car_rent.utils.ScannerUtils;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Menu {
    EntityManager entityManager;
    CarsService carsService;


    public Menu(EntityManager entityManager) {
        this.entityManager = entityManager;
        carsService = new CarsService(entityManager);
    }

    public void printMenu() {
        System.out.println("-".repeat(20));
        System.out.println("Car Rental System Database");
        System.out.println("-".repeat(20));
        System.out.println("1. Rental Transaction");
        System.out.println("2. Rental Data Management");
        System.out.println("3. exit");
    }
    public  void printTransactionMenu() {
        System.out.println("-".repeat(20));
        System.out.println("Transaction Rental Menu");
        System.out.println("-".repeat(20));
        System.out.println("1. Add Transaction");
        System.out.println("2. Show Transaction");
        System.out.println("3. Back");
        String choice = ScannerUtils.inputString("Enter your choice");
        switch (choice) {
            case "1"-> System.out.println("Rental Transaction");
            case "2"-> System.out.println("Rental Data Management");
            case "3"-> printMenu();
        }
    }
    public  void printDataManagementMenu() {
        System.out.println("-".repeat(20));
        System.out.println("Data Management Menu");
        System.out.println("-".repeat(20));
        System.out.println("1. Cars");
        System.out.println("2. Users");
        String choice = ScannerUtils.inputString("Enter your choice");
        switch (choice) {
            case "1"-> printCarsMenu();
            case "2"-> printUserMenu();
            case "3"-> printMenu();
        }
    }
    public  void printCarsMenu() {
        System.out.println("-".repeat(20));
        System.out.println("Cars Menu");
        System.out.println("-".repeat(20));
        System.out.println("1. Add Car");
        System.out.println("2. Show All Car");
        System.out.println("3. Show Car By ID");
        System.out.println("4. Update Car");
        System.out.println("5. Delete Car");
        System.out.println("6. Add Maintenence");
        System.out.println("7. Show Maintenence Records");
        System.out.println("8. Back");
        String choice = ScannerUtils.inputString("Enter your choice");
        switch (choice) {
            case "1"-> carsService.addCars();
            case "2"-> {
                List<Cars> allCars =  carsService.showCars();
                allCars.forEach(System.out::println);
            }
            case "3"-> System.out.println(carsService.showCarsById(Integer.parseInt(ScannerUtils.inputString("Enter Car ID"))).toString());
            case "4"-> carsService.updateCars();
            case "5"-> carsService.deleteCarsById(Integer.parseInt(ScannerUtils.inputString("Enter Car ID")));
            case "6" -> carsService.addMaintenanceRecord();
            case "7" -> {
                List<MaintenanceRecord> maintenenceRecord = carsService.showMaintenanceRecords();
                maintenenceRecord.forEach(System.out::println);
            }
            case "8"-> printDataManagementMenu();
        }
    }
    public void printUserMenu() {
        System.out.println("-".repeat(20));
        System.out.println("User Menu");
        System.out.println("-".repeat(20));
        System.out.println("1. Add User");
        System.out.println("2. Show User");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. Back");
        String choice = ScannerUtils.inputString("Enter your choice");
        switch (choice) {
            case "1"-> System.out.println("Add User");
            case "2"-> System.out.println("Show User");
            case "3"-> System.out.println("Update User");
            case "4"-> System.out.println("Delete User");
            case "5"-> printDataManagementMenu();
        }
    }
}
