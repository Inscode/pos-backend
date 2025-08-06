package com.aasait.pos.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private Double amount;
    private String fileName;
    private LocalDateTime date;

    private String saleInvoiceId;

    // Automatically set the date before saving
    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
    }
}
