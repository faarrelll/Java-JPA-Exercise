package com.enigma.car_rent.service;

import com.enigma.car_rent.constant.car_status;
import com.enigma.car_rent.entity.Cars;
import com.enigma.car_rent.entity.Users;
import com.enigma.car_rent.repository.UsersRepository;
import com.enigma.car_rent.utils.ScannerUtils;
import jakarta.persistence.EntityManager;

import java.util.List;


public class UserService {
    EntityManager entityManager;
    UsersRepository usersRepository;

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.usersRepository = new UsersRepository(entityManager);
    }

    public void addUser(){
        Users user = new Users();
        user.setFullName(ScannerUtils.inputString("Full Name"));
        user.setEmail(ScannerUtils.inputString("Email"));
        user.setPhoneNumber(ScannerUtils.inputString("Phone Number"));
        user.setAddress(ScannerUtils.inputString("Address"));
        user.setDrivingLicenseNum(ScannerUtils.inputString("Driving License Number"));
        user.setPasswordHash(ScannerUtils.inputString("Password Hash"));
        usersRepository.save(user);
    }

    public void updateUser(){
        Integer id = ScannerUtils.inputInterger("User ID");
        Users user = usersRepository.findById(id);
        user.setFullName(ScannerUtils.inputString("Full Name"));
        user.setEmail(ScannerUtils.inputString("Email"));
        user.setPhoneNumber(ScannerUtils.inputString("Phone Number"));
        user.setAddress(ScannerUtils.inputString("Address"));
        user.setDrivingLicenseNum(ScannerUtils.inputString("Driving License Number"));
        user.setPasswordHash(ScannerUtils.inputString("Password Hash"));
        usersRepository.save(user);
    }

    public List<Users> usersList (){
        List<Users> usersList = usersRepository.findAll();
        return usersList;
    }

    public void deleteUser(){
        Integer id = ScannerUtils.inputInterger("User ID");
        Users user = usersRepository.findById(id);
        usersRepository.delete(user);
    }
}
