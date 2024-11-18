package com.enigma.car_rent.entity;

import com.enigma.car_rent.constant.rental_status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "rentals", indexes = {
        @Index(name = "idx_user_car", columnList = "user_id, car_id", unique = true),
        @Index(name = "idx_start_end", columnList = "start_date, end_date")
})
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Cars car;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private rental_status status;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    // Kolom created_at dengan default 'now()' untuk waktu pembuatan
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Kolom updated_at dengan default 'now()' untuk waktu pembaruan
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "id : " + id + "\n"+
                "user : " + user + "\n"+
                "car : " + car + "\n"+
                "startDate : " + startDate + "\n"+
                "endDate : " + endDate + "\n"+
                "status :" + status + "\n"+
                "totalAmount :" + totalAmount + "\n"+
                "createdAt : " + createdAt + "\n"+
                "updatedAt : " + updatedAt;
    }
}
