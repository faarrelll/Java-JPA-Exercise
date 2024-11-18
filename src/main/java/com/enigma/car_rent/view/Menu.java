package com.enigma.car_rent.view;

import com.enigma.car_rent.entity.Cars;
import com.enigma.car_rent.entity.MaintenanceRecord;
import com.enigma.car_rent.entity.Rentals;
import com.enigma.car_rent.entity.Users;
import com.enigma.car_rent.service.CarsService;
import com.enigma.car_rent.service.TransactionService;
import com.enigma.car_rent.service.UserService;
import com.enigma.car_rent.utils.ScannerUtils;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Menu {
    EntityManager entityManager;
    CarsService carsService;
    UserService userService;
    TransactionService transactionService;


    public Menu(EntityManager entityManager) {
        this.entityManager = entityManager;
        carsService = new CarsService(entityManager);
        userService = new UserService(entityManager);
        transactionService = new TransactionService(entityManager);
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
        System.out.println("3. Confirm Payments");
        System.out.println("4. Cancel Transaction");
        System.out.println("5. Return Car");
        System.out.println("6. Back");
        String choice = ScannerUtils.inputString("Enter your choice");
        switch (choice) {
            case "1"-> transactionService.addTransaction();
            case "2"-> {
                List<Rentals> rentals = transactionService.getRentals();
                rentals.forEach(System.out::println);
            }
            case "3"-> transactionService.addPayment();
            case "4"-> transactionService.cancelPayment();
            case "5"-> transactionService.returnCar();
            case "6"-> printMenu();

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
        System.out.println("6. Retire Car");
        System.out.println("7. Add Maintenence");
        System.out.println("8. Show Maintenence Records");
        System.out.println("9. Back");
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
            case "6" -> carsService.retireCar();
            case "7" -> carsService.addMaintenanceRecord();
            case "8" -> {
                List<MaintenanceRecord> maintenenceRecord = carsService.showMaintenanceRecords();
                maintenenceRecord.forEach(System.out::println);
            }
            case "9"-> printDataManagementMenu();
        }
    }
    public void printUserMenu() {
        System.out.println("-".repeat(20));
        System.out.println("User Menu");
        System.out.println("-".repeat(20));
        System.out.println("1. Add User");
        System.out.println("2. Update User");
        System.out.println("3. Show User");
        System.out.println("4. Delete User");
        System.out.println("5. Back");
        String choice = ScannerUtils.inputString("Enter your choice");
        switch (choice) {
            case "1"-> userService.addUser();
            case "2"-> userService.updateUser();
            case "3"-> {
             List<Users> usersList = userService.usersList();
             usersList.forEach(System.out::println);
            }
            case "4"-> userService.deleteUser();
            case "5"-> printDataManagementMenu();
        }
    }
}
