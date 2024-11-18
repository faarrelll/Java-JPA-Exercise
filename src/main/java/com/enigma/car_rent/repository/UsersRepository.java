package com.enigma.car_rent.repository;

import com.enigma.car_rent.entity.Users;
import jakarta.persistence.EntityManager;

public class UsersRepository extends GenericRepository<Users> {

    public UsersRepository(EntityManager entityManager) {
        super(entityManager, Users.class);
    }
}
