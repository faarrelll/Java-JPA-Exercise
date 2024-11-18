package com.enigma.car_rent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "maintenance_records", indexes = {
        @Index(name = "idx_car_id", columnList = "car_id"),
        @Index(name = "idx_maintenance_date", columnList = "maintenance_date")
}
)
public class MaintenanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Cars car; // Relasi dengan tabel `cars`

    @Column(name = "maintenance_date", nullable = false)
    private LocalDate maintenanceDate;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description; // Tipe TEXT untuk deskripsi

    @Column(name = "cost")
    private Double cost;

    @Column(name = "completed_at")
    private LocalDate completedAt;

    // Kolom created_at dengan default 'now()' untuk waktu pembuatan
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Kolom updated_at dengan default 'now()' untuk waktu pembaruan
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Default Constructor, Getter dan Setter sudah disediakan oleh Lomb

    @Override
    public String toString() {
        return  "id : " + id + "\n"+
                "car :" + (car != null ? car.getId()  : "N/A") + "\n"+
                "brand : " + (car != null ? car.getBrand() : "N/A") + "\n"+
                "maintenance Date : " + maintenanceDate + "\n"+
                "description : " + description + "\n"+
                "cost : " + cost + "\n"+
                "completedAt : " + completedAt + "\n"+
                "createdAt : " + createdAt + "\n"+
                "updatedAt: " + updatedAt;
    }
}
