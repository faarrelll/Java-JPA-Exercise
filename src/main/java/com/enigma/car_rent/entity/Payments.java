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
@Table(name = "payments", indexes = {
        @Index(name = "idx_rental", columnList = "rental_id", unique = true),
        @Index(name = "idx_transaction", columnList = "transaction_id", unique = true)
})
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private Rentals rentals;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @CreationTimestamp
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;

    // Kolom created_at dengan default 'now()' untuk waktu pembuatan
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Kolom updated_at dengan default 'now()' untuk waktu pembaruan
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;




}
