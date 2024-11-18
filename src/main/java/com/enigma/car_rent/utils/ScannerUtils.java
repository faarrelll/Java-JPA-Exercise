package com.enigma.car_rent.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ScannerUtils {
    static Scanner scanner = new Scanner(System.in);

    public static String inputString(String text) {
        while (true){
            System.out.print(text + " : ");
            String input = scanner.nextLine();
            if (input == null ||  input.isBlank()) {
                System.out.println(text + " cannot be null");
                System.out.println("Please input again!");
            } else {
                return input;
            }
        }
    }

    public static Integer inputInterger(String text) {
        while (true){
            System.out.print(text + " : ");
            Integer input = Integer.parseInt(scanner.nextLine());
            if (input < 0 || input == null) {
                System.out.println(text + " cannot be lower than 0 and null");
                System.out.println("Please input again!");
            } else {
                return input;
            }
        }
    }

    public static Double inputDouble(String text) {
        while (true){
            System.out.print(text + " : ");
            Double input = Double.parseDouble(scanner.nextLine());
            if (input < 0d || input == null) {
                System.out.println(text + " cannot be lower than 0 and null");
                System.out.println("Please input again!");
            } else {
                return input;
            }
        }
    }

    public static LocalDate inputDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            try {
                System.out.print(text + " (yyyy-MM-dd): ");
                String inputStr = scanner.nextLine();
                return LocalDate.parse(inputStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please use 'yyyy-MM-dd'.");
            }
        }
    }
}
