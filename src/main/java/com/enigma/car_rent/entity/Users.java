package com.enigma.car_rent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "users", indexes = {
        @Index(name = "idx_email", columnList = "email", unique = true),
        @Index(name = "idx_driving_license_number", columnList = "driving_license_number", unique = true)
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "driving_license_number", nullable = false,unique = true)
    private String drivingLicenseNum;

    @Column(name = "password_hash")
    private String passwordHash;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "id :" + id +
                "fullName : " + fullName + "\n" +
                "email : " + email + "\n" +
                "phoneNumber : " + phoneNumber + "\n" +
                "address : " + address + "\n" +
                "drivingLicenseNum : " + drivingLicenseNum + "\n" +
                "passwordHash : " + passwordHash + "\n" +
                "createdAt : " + createdAt + "\n" +
                "updatedAt : " + updatedAt;
    }
}
