package com.enigma.car_rent.entity;

import com.enigma.car_rent.constant.car_status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "cars", indexes = {
        @Index(name = "idx_license_plate", columnList = "license_plate", unique = true),
        @Index(name = "idx_brand_model", columnList = "brand, model")
})
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "color")
    private String color;

    @Column(name = "mileage", nullable = false)
    private Double mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private car_status status;

    @Column(name = "daily_rate")
    private Double dailyRate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<MaintenanceRecord> maintenanceRecords = new ArrayList<>();

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    List<Rentals> rentalRecord = new ArrayList<>();

    @Override
    public String toString() {
        return  "id : " + id + "\n"+
                "brand : " + brand + "\n"+
                "model : " + model + "\n"+
                "year: " + year + "\n"+
                "licensePlate : " + licensePlate  + "\n"+
                "color : " + color + "\n"+
                "mileage : " + mileage + "\n"+
                "status : " + status + "\n"+
                "dailyRate : " + dailyRate + "\n"+
                "created At : " + createdAt + "\n"+
                "updated At : " + updatedAt ;
    }
}
