package com.aasait.pos.backend.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private String type;
    private Double amount;
    private String fileName;
    private String salesInvoiceId; // Reference to Invoice ID
}
