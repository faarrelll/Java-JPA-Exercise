package com.enigma.car_rent;

import com.enigma.car_rent.constant.car_status;
import com.enigma.car_rent.constant.rental_status;
import com.enigma.car_rent.entity.*;
import com.enigma.car_rent.repository.*;
import com.enigma.car_rent.utils.ScannerUtils;
import com.enigma.car_rent.view.Menu;
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
        Menu menu = new Menu(entityManager);
        while (true) {
            menu.printMenu();
            String choice = ScannerUtils.inputString("Enter Your Choice");
            switch (choice) {
                case "1" -> menu.printTransactionMenu();
                case "2" -> menu.printDataManagementMenu();
                case "3" -> {
                    close();
                    System.exit(0);
                }
            }

        }
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



