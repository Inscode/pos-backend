package com.aasait.pos.backend.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private String batchId;
    private String status;
    private double total;
    private double unitPrice;
    private double sellPrice;
}
